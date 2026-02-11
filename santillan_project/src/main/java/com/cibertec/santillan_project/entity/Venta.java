package com.cibertec.santillan_project.entity;


import com.cibertec.santillan_project.enums.DocumentoTipo;
import com.cibertec.santillan_project.enums.PagoTipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ventas")
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public DocumentoTipo getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(DocumentoTipo tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public PagoTipo getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(PagoTipo tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    public List<VentaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getEfectivoRecibido() {
        return efectivoRecibido;
    }

    public void setEfectivoRecibido(BigDecimal efectivoRecibido) {
        this.efectivoRecibido = efectivoRecibido;
    }

    public BigDecimal getCambio() {
        return cambio;
    }

    public void setCambio(BigDecimal cambio) {
        this.cambio = cambio;
    }

    public BigDecimal getDescuentoIGV() {
        return descuentoIGV;
    }

    public void setDescuentoIGV(BigDecimal descuentoIGV) {
        this.descuentoIGV = descuentoIGV;
    }

    public Boolean getExacto() {
        return exacto;
    }

    public void setExacto(Boolean exacto) {
        this.exacto = exacto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @Enumerated(EnumType.STRING)
    private com.cibertec.santillan_project.enums.DocumentoTipo tipoDocumento;

    @Enumerated(EnumType.STRING)
    private com.cibertec.santillan_project.enums.PagoTipo tipoPago;

    @Column(name = "numero_venta")
    private String numeroVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaDetalle> detalles = new ArrayList<>();

    @Column(name = "subtotal", precision = 19, scale = 4)
    private BigDecimal subtotal;

    @Column(name = "igv", precision = 19, scale = 4)
    private BigDecimal igv;

    @Column(name = "total", precision = 19, scale = 4)
    private BigDecimal total;

    @Column(name = "efectivo_recibido", precision = 19, scale = 4)
    private BigDecimal efectivoRecibido;

    @Column(name = "cambio", precision = 19, scale = 4)
    private BigDecimal cambio;

    @Column(name = "descuento_igv", precision = 19, scale = 4)
    private BigDecimal descuentoIGV;

    @Column(name = "is_exacto")
    private Boolean exacto;


}
