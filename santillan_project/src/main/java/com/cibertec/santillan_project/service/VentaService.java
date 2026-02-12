package com.cibertec.santillan_project.service;

import com.cibertec.santillan_project.dto.*;
import com.cibertec.santillan_project.entity.Producto;
import com.cibertec.santillan_project.entity.Venta;
import com.cibertec.santillan_project.entity.VentaDetalle;
import com.cibertec.santillan_project.repository.OrdenCompraRepository;
import com.cibertec.santillan_project.repository.ProductoRepository;
import com.cibertec.santillan_project.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final ProductoRepository productoRepo;
    private final VentaRepository ventaRepo;
    private final OrdenCompraRepository ordenCompraRepo;

    public VentaService(ProductoRepository productoRepo, VentaRepository ventaRepo, OrdenCompraRepository ordenCompraRepo) {
        this.productoRepo = productoRepo;
        this.ventaRepo = ventaRepo;
        this.ordenCompraRepo = ordenCompraRepo;
    }

    @Transactional
    public VentaResponse registrarVenta(CreateVentaRequest request) {
        Venta venta = new Venta();
        venta.setFechaVenta(LocalDateTime.now());
        venta.setNumeroVenta(request.getNumeroVenta());
        venta.setTipoDocumento(request.getTipoDocumento());
        venta.setTipoPago(request.getTipoPago());
        venta.setEfectivoRecibido(request.getEfectivoRecibido());
        venta.setExacto(request.getExacto());
        venta.setDescuentoIGV(request.getDescuentoIGV());

        List<VentaDetalle> detalles = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;

        for (VentaProductoDTO item : request.getItems()) {
            Producto p = productoRepo.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductoId()));

            VentaDetalle det = new VentaDetalle();
            det.setVenta(venta);
            det.setProducto(p);
            det.setCantidad(item.getCantidad());
            det.setPrecioUnitario(p.getPrecioUnitario());
            BigDecimal lineSubtotal = p.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad()));
            det.setSubtotal(lineSubtotal);
            subtotal = subtotal.add(lineSubtotal);
            detalles.add(det);
        }

        venta.setDetalles(detalles);

        BigDecimal igv = subtotal.multiply(BigDecimal.valueOf(0.18));
        venta.setSubtotal(subtotal);
        venta.setIgv(igv);

        BigDecimal total = subtotal.add(igv);
        if (request.getDescuentoIGV() != null) {
            total = total.subtract(request.getDescuentoIGV());
            venta.setDescuentoIGV(request.getDescuentoIGV());
        }
        venta.setTotal(total);

        BigDecimal efectivo = request.getEfectivoRecibido() != null ? request.getEfectivoRecibido() : BigDecimal.ZERO;
        venta.setCambio(efectivo.subtract(total));

        Venta saved = ventaRepo.save(venta);

        VentaResponse resp = new VentaResponse();
        resp.setId(saved.getId());
        resp.setFechaVenta(saved.getFechaVenta());
        resp.setNumeroVenta(saved.getNumeroVenta());
        resp.setTipoDocumento(saved.getTipoDocumento().name());
        resp.setTipoPago(saved.getTipoPago().name());
        resp.setSubtotal(saved.getSubtotal());
        resp.setIgv(saved.getIgv());
        resp.setDescuentoIGV(saved.getDescuentoIGV());
        resp.setTotal(saved.getTotal());
        resp.setEfectivoRecibido(saved.getEfectivoRecibido());
        resp.setCambio(saved.getCambio());
        resp.setExacto(saved.getExacto());

        List<VentaDetalleDTO> dets = saved.getDetalles().stream().map(d -> {
            VentaDetalleDTO dto = new VentaDetalleDTO();
            dto.setProductoId(d.getProducto().getId());
            dto.setNombre(d.getProducto().getNombre());
            dto.setCantidad(d.getCantidad());
            dto.setPrecioUnitario(d.getPrecioUnitario());
            dto.setSubtotal(d.getSubtotal());
            return dto;
        }).collect(Collectors.toList());

        resp.setDetalles(dets);
        return resp;
    }

    @Transactional
    public List<VentaResponse> listarVentas() {
        return ventaRepo.findAll().stream().map(v -> {
            VentaResponse resp = new VentaResponse();
            resp.setId(v.getId());
            resp.setFechaVenta(v.getFechaVenta());
            resp.setNumeroVenta(v.getNumeroVenta());
            resp.setTipoDocumento(v.getTipoDocumento().name());
            resp.setTipoPago(v.getTipoPago().name());
            resp.setSubtotal(v.getSubtotal());
            resp.setIgv(v.getIgv());
            resp.setTotal(v.getTotal());
            resp.setEfectivoRecibido(v.getEfectivoRecibido());
            resp.setCambio(v.getCambio());
            resp.setExacto(v.getExacto());

            List<VentaDetalleDTO> dets = v.getDetalles().stream().map(d -> {
                VentaDetalleDTO dto = new VentaDetalleDTO();
                dto.setProductoId(d.getProducto().getId());
                dto.setNombre(d.getProducto().getNombre());
                dto.setCantidad(d.getCantidad());
                dto.setPrecioUnitario(d.getPrecioUnitario());
                dto.setSubtotal(d.getSubtotal());
                return dto;
            }).collect(Collectors.toList());

            resp.setDetalles(dets);
            return resp;
        }).collect(Collectors.toList());
    }

    @Transactional
    public VentaResponse obtenerVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        VentaResponse resp = new VentaResponse();
        resp.setId(v.getId());
        resp.setFechaVenta(v.getFechaVenta());
        resp.setNumeroVenta(v.getNumeroVenta());
        resp.setTipoDocumento(v.getTipoDocumento().name());
        resp.setTipoPago(v.getTipoPago().name());
        resp.setSubtotal(v.getSubtotal());
        resp.setIgv(v.getIgv());
        resp.setTotal(v.getTotal());
        resp.setEfectivoRecibido(v.getEfectivoRecibido());
        resp.setCambio(v.getCambio());
        resp.setExacto(v.getExacto());

        List<VentaDetalleDTO> dets = v.getDetalles().stream().map(d -> {
            VentaDetalleDTO dto = new VentaDetalleDTO();
            dto.setProductoId(d.getProducto().getId());
            dto.setNombre(d.getProducto().getNombre());
            dto.setCantidad(d.getCantidad());
            dto.setPrecioUnitario(d.getPrecioUnitario());
            dto.setSubtotal(d.getSubtotal());
            return dto;
        }).collect(Collectors.toList());

        resp.setDetalles(dets);
        return resp;
    }

    // NUEVOS MÉTODOS

    @Transactional
    public GananciasDiaDTO obtenerGananciasDia() {
        LocalDateTime inicioDia = LocalDate.now().atStartOfDay();
        LocalDateTime finDia = LocalDate.now().atTime(LocalTime.MAX);

        BigDecimal totalVentas = ventaRepo.sumTotalByFechaBetween(inicioDia, finDia);
        BigDecimal totalCostos = ordenCompraRepo.sumTotalByFechaBetween(inicioDia, finDia);

        // Si totalVentas o totalCostos es null, usar 0---------------------new---
        if (totalVentas == null) totalVentas = BigDecimal.ZERO;
        if (totalCostos == null) totalCostos = BigDecimal.ZERO;

        BigDecimal ganancias = totalVentas.subtract(totalCostos);

        return new GananciasDiaDTO(ganancias);
    }

    @Transactional
    public ResumenVentasDTO obtenerResumenMensual() {

        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioMes = hoy.withDayOfMonth(1).atStartOfDay();
        LocalDateTime finMes = hoy.withDayOfMonth(hoy.lengthOfMonth()).atTime(LocalTime.MAX);

        BigDecimal totalVentasMes = ventaRepo.sumTotalByFechaBetween(inicioMes, finMes);
        // Total de ventas del mes----------------------------------
        if (totalVentasMes == null) totalVentasMes = BigDecimal.ZERO;
        // Total de costos del mes (órdenes de compra)-----------------------------------
        BigDecimal totalCostosMes = ordenCompraRepo.sumTotalByFechaBetween(inicioMes, finMes);
        if (totalCostosMes == null) totalCostosMes = BigDecimal.ZERO;

        List<Venta> ventasDelMes = ventaRepo.findByFechaVentaBetween(inicioMes, finMes);

        Map<Long, ProductoStats> statsMap = new HashMap<>();
        BigDecimal gananciaNetaTotal = BigDecimal.ZERO;

        for (Venta venta : ventasDelMes) {
            for (VentaDetalle detalle : venta.getDetalles()) {

                Long prodId = detalle.getProducto().getId();
                String prodNombre = detalle.getProducto().getNombre();

                ProductoStats stats = statsMap.computeIfAbsent(
                        prodId, k -> new ProductoStats(prodId, prodNombre)
                );

                stats.cantidadVendida += detalle.getCantidad();

                // GANANCIA = PRECIO UNITARIO × CANTIDAD
                BigDecimal ganancia =
                        detalle.getPrecioUnitario()
                                .multiply(BigDecimal.valueOf(detalle.getCantidad()));

                stats.totalVentas = stats.totalVentas.add(ganancia);
                gananciaNetaTotal = gananciaNetaTotal.add(ganancia);
            }
        }

        List<ProductoRentableDTO> productosRentables = statsMap.values().stream()
                .map(stats -> {
                    // 🔹 Ganancia del producto = 30% del total vendido
                    BigDecimal gananciaProducto = stats.totalVentas
                            .multiply(BigDecimal.valueOf(0.30));

                    return new ProductoRentableDTO(
                            stats.productoId,
                            stats.nombreProducto,
                            gananciaProducto,
                            stats.cantidadVendida
                    );
                })
                .sorted((a, b) -> b.getGananciaTotal().compareTo(a.getGananciaTotal()))
                .limit(3)
                .collect(Collectors.toList());


        // 🔹 Productos más vendidos (por cantidad)
        List<ProductoVendidoDTO> productosVendidos = statsMap.values().stream()
                .map(stats -> new ProductoVendidoDTO(
                        stats.productoId,
                        stats.nombreProducto,
                        stats.cantidadVendida,
                        stats.totalVentas
                ))
                .sorted((a, b) -> b.getCantidadVendida().compareTo(a.getCantidadVendida()))
                .limit(3)
                .collect(Collectors.toList());



        ResumenVentasDTO resumen = new ResumenVentasDTO();
        resumen.setTotalVentasMes(totalVentasMes);
        resumen.setTotalCostosMes(totalCostosMes);
        resumen.setGananciaBruta(gananciaNetaTotal); // ahora es solo precio × cantidad
        resumen.setProductosRentables(productosRentables);
        resumen.setProductosVendidos(productosVendidos);

        return resumen;
    }


    private static class ProductoStats {
        Long productoId;
        String nombreProducto;
        Integer cantidadVendida = 0;
        BigDecimal totalVentas = BigDecimal.ZERO;

        ProductoStats(Long productoId, String nombreProducto) {
            this.productoId = productoId;
            this.nombreProducto = nombreProducto;
        }
    }
}