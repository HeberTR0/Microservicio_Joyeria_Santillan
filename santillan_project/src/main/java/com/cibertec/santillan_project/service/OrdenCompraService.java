package com.cibertec.santillan_project.service;
import com.cibertec.santillan_project.entity.*;
import com.cibertec.santillan_project.dto.*;
import com.cibertec.santillan_project.enums.EstadoOrden;
import com.cibertec.santillan_project.enums.MovimientoTipo;
import com.cibertec.santillan_project.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrdenCompraService {

    private ProveedorRepository proveedorRepo;
    private ProductoRepository productoRepo;
    private OrdenCompraRepository ordenRepo;

    private MovimientoInventarioRepository movRepo;

    public OrdenCompraService(ProveedorRepository proveedorRepo,
                              ProductoRepository productoRepo,
                              OrdenCompraRepository ordenRepo,
                              MovimientoInventarioRepository movRepo) {
        this.proveedorRepo = proveedorRepo;
        this.productoRepo = productoRepo;
        this.ordenRepo = ordenRepo;
        this.movRepo = movRepo;
    }
    @Transactional
    public OrdenResponse registrarOrden(CreateOrdenRequest request) {
        Proveedor proveedor = proveedorRepo.findById(request.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        OrdenCompra orden = new OrdenCompra();
        orden.setProveedor(proveedor);
        orden.setFechaCreacion(LocalDateTime.now());
        orden.setEstado(EstadoOrden.PENDIENTE);

        List<DetalleOrden> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrdenProductoDTO item : request.getItems()) {
            Producto producto = productoRepo.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductoId()));

            DetalleOrden detalle = new DetalleOrden();
            detalle.setOrdenCompra(orden);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());

            BigDecimal precioUnitario = producto.getPrecioUnitario();
            detalle.setPrecioUnitario(precioUnitario);
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(item.getCantidad()));
            detalle.setSubtotal(subtotal);

            total = total.add(subtotal);
            detalles.add(detalle);
        }

        orden.setDetalles(detalles);
        orden.setTotal(total);

        OrdenCompra saved = ordenRepo.save(orden);

        return toOrdenResponse(saved);
    }
    @Transactional
    public List<OrdenResponse> listarOrdenes(EstadoOrden estado) {
        List<OrdenCompra> ordenes;

        if (estado == null) {
            ordenes = ordenRepo.findAll();
        } else {
            ordenes = ordenRepo.findByEstado(estado);
        }

        return ordenes.stream()
                .map(this::toOrdenResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public OrdenResponse obtenerOrden(Long id) {
        OrdenCompra orden = ordenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        // Detalle y asociaciones ya están en la entidad; mapear a DTO
        return toOrdenResponse(orden);
    }

    @Transactional
    public OrdenResponse confirmarRecepcion(RecepcionRequest request) {
        OrdenCompra orden = ordenRepo.findById(request.getOrdenId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (orden.getEstado() != EstadoOrden.PENDIENTE) {
            throw new RuntimeException("La orden no está en estado PENDIENTE");
        }

        // Actualizar stock y movimientos
        LocalDateTime now = LocalDateTime.now();
        BigDecimal totalRecibido = BigDecimal.ZERO;

        Map<Long, Integer> recibidosPorProducto = request.getItems().stream()
                .collect(Collectors.toMap(RecepcionItemDTO::getProductoId, RecepcionItemDTO::getCantidadRecibida));

        for (DetalleOrden detalle : orden.getDetalles()) {
            Long pid = detalle.getProducto().getId();
            int recibidos = recibidosPorProducto.getOrDefault(pid, 0);
            if (recibidos > 0) {
                Producto p = detalle.getProducto();
                p.setStock(p.getStock() + recibidos);
                //NUEVO----------------------------------
                productoRepo.save(p);

                MovimientoInventario mov = new MovimientoInventario();
                mov.setProducto(p);
                mov.setTipo(MovimientoTipo.ENTRADA);
                mov.setCantidad(recibidos);
                mov.setFechaMovimiento(now);
                mov.setMotivo("Recepción orden: " + orden.getId());
                //NUEVO----------------------------------
                movRepo.save(mov);



                totalRecibido = totalRecibido.add(detalle.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(recibidos)));
            }
        }


        orden.setEstado(EstadoOrden.RECIBIDA);
        OrdenCompra saved = ordenRepo.save(orden);


        return toOrdenResponse(saved);
    }


    private OrdenResponse toOrdenResponse(OrdenCompra orden) {
        OrdenResponse resp = new OrdenResponse();
        resp.setId(orden.getId());

        ProveedorDTO prov = new ProveedorDTO();
        prov.setId(orden.getProveedor().getId());
        prov.setNombre(orden.getProveedor().getNombre());


        resp.setProveedor(prov);
        resp.setFechaCreacion(orden.getFechaCreacion());
        resp.setEstado(orden.getEstado().name());
        resp.setTotal(orden.getTotal());

        List<DetalleOrdenDTO> dets = orden.getDetalles().stream().map(d -> {
            DetalleOrdenDTO dto = new DetalleOrdenDTO();
            ProductoDTO p = new ProductoDTO();
            p.setId(d.getProducto().getId());
            p.setNombre(d.getProducto().getNombre());
            dto.setProducto(p);
            dto.setCantidad(d.getCantidad());
            dto.setPrecioUnitario(d.getPrecioUnitario());
            dto.setSubtotal(d.getSubtotal());
            return dto;
        }).collect(Collectors.toList());

        resp.setDetalles(dets);
        return resp;
    }




}