package com.cibertec.santillan_project.dto;

import java.math.BigDecimal;

public class ProductoVendidoDTO {
    private Long productoId;
    private String nombreProducto;
    private Integer cantidadVendida;
    private BigDecimal totalVentas;

    public ProductoVendidoDTO() {
    }

    public ProductoVendidoDTO(Long productoId, String nombreProducto, Integer cantidadVendida, BigDecimal totalVentas) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
        this.totalVentas = totalVentas;
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

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public BigDecimal getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(BigDecimal totalVentas) {
        this.totalVentas = totalVentas;
    }
}