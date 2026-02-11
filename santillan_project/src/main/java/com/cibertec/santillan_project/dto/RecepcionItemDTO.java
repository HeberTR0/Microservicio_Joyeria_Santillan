package com.cibertec.santillan_project.dto;

import lombok.Data;

@Data
public class RecepcionItemDTO {

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    private Long productoId;
    private Integer cantidadRecibida;


}
