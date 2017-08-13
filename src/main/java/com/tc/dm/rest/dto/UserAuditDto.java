package com.tc.dm.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAuditDto {

    protected List<ItemDto> itemDtos;
    protected List<CollectionDto> collectionDtos;

    public List<ItemDto> getItemDtos() {
        if(null == itemDtos) itemDtos = new ArrayList<>();
        return itemDtos;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

    public List<CollectionDto> getCollectionDtos() {
        if(null == collectionDtos) collectionDtos = new ArrayList<>();
        return collectionDtos;
    }

    public void setCollectionDtos(List<CollectionDto> collectionDtos) {
        this.collectionDtos = collectionDtos;
    }

    public static UserAuditDto getInstance() {
        return new UserAuditDto();
    }
}
