package com.cibertec.santillan_project.dto;

import com.cibertec.santillan_project.enums.DocumentoTipo;
import com.cibertec.santillan_project.enums.PagoTipo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateVentaRequest {
    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
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

    public BigDecimal getEfectivoRecibido() {
        return efectivoRecibido;
    }

    public void setEfectivoRecibido(BigDecimal efectivoRecibido) {
        this.efectivoRecibido = efectivoRecibido;
    }

    public Boolean getExacto() {
        return exacto;
    }

    public void setExacto(Boolean exacto) {
        this.exacto = exacto;
    }

    public BigDecimal getDescuentoIGV() {
        return descuentoIGV;
    }

    public void setDescuentoIGV(BigDecimal descuentoIGV) {
        this.descuentoIGV = descuentoIGV;
    }

    public List<VentaProductoDTO> getItems() {
        return items;
    }

    public void setItems(List<VentaProductoDTO> items) {
        this.items = items;
    }

    private String numeroVenta;
    private DocumentoTipo tipoDocumento;
    private PagoTipo tipoPago;
    private BigDecimal efectivoRecibido;
    private Boolean exacto;
    private BigDecimal descuentoIGV;
    private List<VentaProductoDTO> items;
}
