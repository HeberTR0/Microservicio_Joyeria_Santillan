package com.cibertec.santillan_project.dto;

import com.cibertec.santillan_project.enums.CotizacionEstado;
import com.cibertec.santillan_project.enums.TipoProductoCotizacion;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CotizacionResponse {
    private Long id;
    private TipoProductoCotizacion tipoProducto;
    private String material;
    private Integer kilataje;
    private BigDecimal pesoEstimado;
    private String tipoPiedra;
    private BigDecimal quilataje;
    private String calidadPiedra;
    private BigDecimal costoManoObra;
    private BigDecimal precioEstimado;
    private LocalDateTime fecha;
    private CotizacionEstado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoProductoCotizacion getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProductoCotizacion tipoProducto) {
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

    public BigDecimal getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(BigDecimal precioEstimado) {
        this.precioEstimado = precioEstimado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public CotizacionEstado getEstado() {
        return estado;
    }

    public void setEstado(CotizacionEstado estado) {
        this.estado = estado;
    }
}
