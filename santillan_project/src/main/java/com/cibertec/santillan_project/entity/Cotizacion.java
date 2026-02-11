package com.cibertec.santillan_project.entity;

import com.cibertec.santillan_project.enums.CotizacionEstado;
import com.cibertec.santillan_project.enums.TipoProductoCotizacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cotizaciones")
@NoArgsConstructor
@AllArgsConstructor
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_producto")
    private TipoProductoCotizacion tipoProducto; // ANILLO, RELOJ

    private String material; // e.g., "oro amarillo", "oro blanco", "plata", "acero"

    @Column(name = "kilataje")
    private Integer kilataje; // en quilates (p.ej., 18)

    @Column(name = "peso_estimado", precision = 19, scale = 4)
    private BigDecimal pesoEstimado; // en gramos

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

    @Column(name = "tipo_piedra")
    private String tipoPiedra;

    @Column(name = "quilataje", precision = 19, scale = 4)
    private BigDecimal quilataje; // peso de piedras en quilates (ct)

    @Column(name = "calidad_piedra")
    private String calidadPiedra;

    @Column(name = "costo_mano_obra", precision = 19, scale = 4)
    private BigDecimal costoManoObra;

    @Column(name = "precio_estimado", precision = 19, scale = 4)
    private BigDecimal precioEstimado;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private CotizacionEstado estado; // COTIZADA, APROBADA, CONVERTIDA
}
