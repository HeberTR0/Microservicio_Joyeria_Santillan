package com.cibertec.santillan_project.dto;

import java.math.BigDecimal;

public class GananciasDiaDTO {
    private BigDecimal gananciasDia;

    public GananciasDiaDTO() {
    }

    public GananciasDiaDTO(BigDecimal gananciasDia) {
        this.gananciasDia = gananciasDia;
    }

    public BigDecimal getGananciasDia() {
        return gananciasDia;
    }

    public void setGananciasDia(BigDecimal gananciasDia) {
        this.gananciasDia = gananciasDia;
    }
}