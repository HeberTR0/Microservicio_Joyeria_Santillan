package com.cibertec.santillan_project.repository;

import com.cibertec.santillan_project.entity.VentaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<VentaDetalle, Long> {
}
