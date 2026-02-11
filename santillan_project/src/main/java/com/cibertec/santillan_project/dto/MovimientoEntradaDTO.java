package com.cibertec.santillan_project.dto;

import lombok.Data;

@Data
public class MovimientoEntradaDTO {

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    private Long productoId;
    private int cantidad;
    private String motivo;
}
