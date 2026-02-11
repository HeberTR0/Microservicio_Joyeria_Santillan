package com.cibertec.santillan_project.controller;

import com.cibertec.santillan_project.entity.Producto;
import com.cibertec.santillan_project.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private ProductoRepository productoRepo;

    public ProductoController(ProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto saved = productoRepo.save(producto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Producto> listar() {
        return productoRepo.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody Producto productoActualizado) {

        return productoRepo.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setPrecioUnitario(productoActualizado.getPrecioUnitario());
                    producto.setStock(productoActualizado.getStock());
                    Producto actualizado = productoRepo.save(producto);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return productoRepo.findById(id)
                .map(producto -> {
                    productoRepo.delete(producto);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}