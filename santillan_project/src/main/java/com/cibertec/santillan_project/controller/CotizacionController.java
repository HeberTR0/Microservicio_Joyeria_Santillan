package com.cibertec.santillan_project.controller;

import com.cibertec.santillan_project.dto.CotizacionResponse;
import com.cibertec.santillan_project.dto.CreateCotizacionRequest;
import com.cibertec.santillan_project.service.CotizacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionController {
    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }


    @PostMapping
    public ResponseEntity<CotizacionResponse> registrarCotizacion(@RequestBody CreateCotizacionRequest request) {
        CotizacionResponse resp = cotizacionService.registrarCotizacion(request);
        return ResponseEntity.ok(resp);
    }


    @GetMapping
    public ResponseEntity<List<CotizacionResponse>> listarCotizaciones() {
        List<CotizacionResponse> list = cotizacionService.listarCotizaciones();
        return ResponseEntity.ok(list);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCotizacion(@PathVariable Long id) {
        cotizacionService.eliminarCotizacion(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionEstimationResponse> cotizar(@RequestBody CreateCotizacionRequest request) {
        java.math.BigDecimal precio = cotizacionService.cotizar(request);
        CotizacionEstimationResponse res = new CotizacionEstimationResponse();
        res.setPrecioEstimado(precio);
        return ResponseEntity.ok(res);
    }


    public static class CotizacionEstimationResponse {
        private BigDecimal precioEstimado;

        public BigDecimal getPrecioEstimado() {
            return precioEstimado;
        }

        public void setPrecioEstimado(BigDecimal precioEstimado) {
            this.precioEstimado = precioEstimado;
        }
    }
}
