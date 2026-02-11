package com.cibertec.santillan_project.dto;

import lombok.Data;

import java.util.List;
@Data
public class RecepcionRequest {

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public List<RecepcionItemDTO> getItems() {
        return items;
    }

    public void setItems(List<RecepcionItemDTO> items) {
        this.items = items;
    }

    private Long ordenId;

    private List<RecepcionItemDTO> items;


}
