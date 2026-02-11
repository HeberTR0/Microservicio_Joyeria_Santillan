package com.cibertec.santillan_project.controller;

import com.cibertec.santillan_project.dto.MovimientoEntradaDTO;
import com.cibertec.santillan_project.entity.MovimientoInventario;
import com.cibertec.santillan_project.entity.Producto;
import com.cibertec.santillan_project.enums.MovimientoTipo;
import com.cibertec.santillan_project.repository.MovimientoInventarioRepository;
import com.cibertec.santillan_project.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    private ProductoRepository productoRepo;
    private MovimientoInventarioRepository movRepo;

    public InventarioController(ProductoRepository productoRepo, MovimientoInventarioRepository movRepo) {
        this.productoRepo = productoRepo;
        this.movRepo = movRepo;
    }

    @PostMapping("/entrada")
    public ResponseEntity<MovimientoInventario> entrada(@RequestBody MovimientoEntradaDTO dto) {
        Producto p = productoRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + dto.getProductoId()));


        p.setStock(p.getStock() + dto.getCantidad());
        productoRepo.save(p);


        MovimientoInventario mov = new MovimientoInventario();
        mov.setProducto(p);
        mov.setTipo(MovimientoTipo.ENTRADA);
        mov.setCantidad(dto.getCantidad());
        mov.setFechaMovimiento(LocalDateTime.now());
        mov.setMotivo(dto.getMotivo());

        MovimientoInventario saved = movRepo.save(mov);
        return ResponseEntity.ok(saved);
    }
}