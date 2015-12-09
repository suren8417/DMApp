package com.tc.dm.rest.dto;

import java.util.List;

/**
 * Created by sg40304 on 12/9/15.
 */
public class CollectionResponseDto {

    private String status;
    private String message;
    private List<ItemDto> itemDtos;
    private List<CollectionDto> collectionDto;

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

    public List<CollectionDto> getCollectionDto() {
        return collectionDto;
    }

    public void setCollectionDto(List<CollectionDto> collectionDto) {
        this.collectionDto = collectionDto;
    }
}
