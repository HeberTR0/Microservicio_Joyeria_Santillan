package com.cibertec.santillan_project.dto;

import java.math.BigDecimal;
import java.util.List;

public class ResumenVentasDTO {
    private BigDecimal totalVentasMes;
    private BigDecimal totalCostosMes;
    private BigDecimal gananciaBruta;
    private List<ProductoRentableDTO> productosRentables;
    private List<ProductoVendidoDTO> productosVendidos;

    public ResumenVentasDTO() {
    }

    public BigDecimal getTotalVentasMes() {
        return totalVentasMes;
    }

    public void setTotalVentasMes(BigDecimal totalVentasMes) {
        this.totalVentasMes = totalVentasMes;
    }

    public BigDecimal getTotalCostosMes() {
        return totalCostosMes;
    }

    public void setTotalCostosMes(BigDecimal totalCostosMes) {
        this.totalCostosMes = totalCostosMes;
    }

    public BigDecimal getGananciaBruta() {
        return gananciaBruta;
    }

    public void setGananciaBruta(BigDecimal gananciaBruta) {
        this.gananciaBruta = gananciaBruta;
    }

    public List<ProductoRentableDTO> getProductosRentables() {
        return productosRentables;
    }

    public void setProductosRentables(List<ProductoRentableDTO> productosRentables) {
        this.productosRentables = productosRentables;
    }

    public List<ProductoVendidoDTO> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoVendidoDTO> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }
}