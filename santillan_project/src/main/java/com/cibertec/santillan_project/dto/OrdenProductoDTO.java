package com.cibertec.santillan_project.dto;

import lombok.Data;

@Data
public class OrdenProductoDTO {

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    private Long productoId;

    private Integer cantidad;


}
