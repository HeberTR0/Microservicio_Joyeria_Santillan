package com.cibertec.santillan_project.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
@Data
public class CreateOrdenRequest {

    public Long getProveedorId() {
        return proveedorId;
    }


    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public List<OrdenProductoDTO> getItems() {
        return items;
    }

    public void setItems(List<OrdenProductoDTO> items) {
        this.items = items;
    }

    private Long proveedorId;


    private Long empleadoId;


    private List<OrdenProductoDTO> items;


}
