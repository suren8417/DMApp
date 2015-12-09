package com.tc.dm.rest.dto;

import java.util.List;

/**
 * Created by sg40304 on 12/9/15.
 */
public class ItemResponseDto {

    private String status;
    private String message;
    private List<ItemDto> itemDtos;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }
}
