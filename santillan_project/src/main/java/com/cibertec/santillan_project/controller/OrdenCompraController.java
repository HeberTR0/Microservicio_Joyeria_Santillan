package com.cibertec.santillan_project.controller;

import com.cibertec.santillan_project.dto.CreateOrdenRequest;
import com.cibertec.santillan_project.dto.OrdenResponse;
import com.cibertec.santillan_project.dto.RecepcionRequest;
import com.cibertec.santillan_project.enums.EstadoOrden;
import com.cibertec.santillan_project.service.OrdenCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrdenCompraController {

    private OrdenCompraService service;
    public OrdenCompraController(OrdenCompraService service) {
        this.service = service;
    }

    @PostMapping("/ordenes")
    public ResponseEntity<OrdenResponse> registrarOrden(@RequestBody CreateOrdenRequest request) {
        OrdenResponse response = service.registrarOrden(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/ordenes")
    public ResponseEntity<List<OrdenResponse>> listarOrdenes(
            @RequestParam(required = false) String estado
    ) {
        EstadoOrden filtro = null;

        if (estado != null) {
            filtro = EstadoOrden.valueOf(estado.toUpperCase());
        }

        List<OrdenResponse> lista = service.listarOrdenes(filtro);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/ordenes/{id}")
    public ResponseEntity<OrdenResponse> obtenerOrden(@PathVariable Long id) {
        OrdenResponse response = service.obtenerOrden(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/ordenes/recepcion") // o /ordenes/{id}/recepcion
    public ResponseEntity<OrdenResponse> confirmarRecepcion(@RequestBody RecepcionRequest request) {
        OrdenResponse response = service.confirmarRecepcion(request);
        return ResponseEntity.ok(response);
    }
































}