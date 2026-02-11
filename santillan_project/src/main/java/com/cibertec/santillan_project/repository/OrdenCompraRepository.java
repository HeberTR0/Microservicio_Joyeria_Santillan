package com.cibertec.santillan_project.repository;

import com.cibertec.santillan_project.entity.OrdenCompra;
import com.cibertec.santillan_project.enums.EstadoOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
    List<OrdenCompra> findByEstado(EstadoOrden estado);

    @Query("SELECT COALESCE(SUM(o.total), 0) FROM OrdenCompra o WHERE o.fechaCreacion BETWEEN :inicio AND :fin AND o.estado = 'RECIBIDA'")
    BigDecimal sumTotalByFechaBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}