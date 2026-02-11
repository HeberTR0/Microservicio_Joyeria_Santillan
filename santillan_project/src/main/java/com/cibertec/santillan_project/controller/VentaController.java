package com.cibertec.santillan_project.controller;

import com.cibertec.santillan_project.dto.CreateVentaRequest;
import com.cibertec.santillan_project.dto.GananciasDiaDTO;
import com.cibertec.santillan_project.dto.ResumenVentasDTO;
import com.cibertec.santillan_project.dto.VentaResponse;
import com.cibertec.santillan_project.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaResponse> registrar(@RequestBody CreateVentaRequest request) {
        VentaResponse resp = ventaService.registrarVenta(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<List<VentaResponse>> listar() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVenta(id));
    }

    @GetMapping("/ganancias-dia")
    public ResponseEntity<GananciasDiaDTO> obtenerGananciasDia() {
        return ResponseEntity.ok(ventaService.obtenerGananciasDia());
    }

    @GetMapping("/resumen-mensual")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ResumenVentasDTO> obtenerResumenMensual() {
        return ResponseEntity.ok(ventaService.obtenerResumenMensual());
    }
}