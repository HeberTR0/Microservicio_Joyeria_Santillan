package com.cibertec.santillan_project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCotizacionRequest {
    private String tipoProducto;
    private String material;
    private Integer kilataje;
    private BigDecimal pesoEstimado;
    private String tipoPiedra;
    private BigDecimal quilataje;
    private String calidadPiedra;
    private BigDecimal costoManoObra;

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getKilataje() {
        return kilataje;
    }

    public void setKilataje(Integer kilataje) {
        this.kilataje = kilataje;
    }

    public BigDecimal getPesoEstimado() {
        return pesoEstimado;
    }

    public void setPesoEstimado(BigDecimal pesoEstimado) {
        this.pesoEstimado = pesoEstimado;
    }

    public String getTipoPiedra() {
        return tipoPiedra;
    }

    public void setTipoPiedra(String tipoPiedra) {
        this.tipoPiedra = tipoPiedra;
    }

    public BigDecimal getQuilataje() {
        return quilataje;
    }

    public void setQuilataje(BigDecimal quilataje) {
        this.quilataje = quilataje;
    }

    public String getCalidadPiedra() {
        return calidadPiedra;
    }

    public void setCalidadPiedra(String calidadPiedra) {
        this.calidadPiedra = calidadPiedra;
    }

    public BigDecimal getCostoManoObra() {
        return costoManoObra;
    }

    public void setCostoManoObra(BigDecimal costoManoObra) {
        this.costoManoObra = costoManoObra;
    }
}
