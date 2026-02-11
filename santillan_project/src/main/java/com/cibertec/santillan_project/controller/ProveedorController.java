package com.cibertec.santillan_project.controller;

import com.cibertec.santillan_project.entity.Proveedor;
import com.cibertec.santillan_project.repository.ProveedorRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorRepository proveedorRepo;

    public ProveedorController(ProveedorRepository proveedorRepo) {
        this.proveedorRepo = proveedorRepo;
    }


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Proveedor proveedor) {
        try {
            Proveedor saved = proveedorRepo.save(proveedor);
            return ResponseEntity.ok(saved);

        }catch (Exception ex) {

            ex.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar proveedor");
        }
    }


    @GetMapping
    public List<Proveedor> listar() {
        return proveedorRepo.findAll();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {

        Proveedor existing = proveedorRepo.findById(id)
                .orElse(null);

        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado");
        }

        existing.setNombre(proveedor.getNombre());
        existing.setRuc(proveedor.getRuc());
        existing.setTelefono(proveedor.getTelefono());
        existing.setDireccion(proveedor.getDireccion());
        existing.setEmail(proveedor.getEmail());

        proveedorRepo.save(existing);

        return ResponseEntity.ok(existing);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        if (!proveedorRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado");
        }

        try {
            proveedorRepo.deleteById(id);
            return ResponseEntity.ok("Proveedor eliminado");

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar: el proveedor está siendo usado en otros registros.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al eliminar el proveedor.");
        }
    }
}