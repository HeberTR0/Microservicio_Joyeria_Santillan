package com.cibertec.santillan_project.dto;

import java.math.BigDecimal;

public class ProductoRentableDTO {
    private Long productoId;
    private String nombreProducto;
    private BigDecimal gananciaTotal;
    private Integer cantidadVendida;

    public ProductoRentableDTO() {
    }

    public ProductoRentableDTO(Long productoId, String nombreProducto, BigDecimal gananciaTotal, Integer cantidadVendida) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.gananciaTotal = gananciaTotal;
        this.cantidadVendida = cantidadVendida;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(BigDecimal gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
}