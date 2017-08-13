package com.tc.dm.rest.dto;

import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionDto {

    private Long id;
    private String name;
    private String description;
    private List<ItemDto> itemDtos;

    public AuditRevisionDto auditInfo = new AuditRevisionDto();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemDto> getItemDtos() {
        if(null == this.itemDtos) {
            this.itemDtos = new ArrayList<>();
        }
        return itemDtos;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

    public AuditRevisionDto getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(AuditRevisionDto auditInfo) {
        this.auditInfo = auditInfo;
    }

    public Collection toCollection() {
        Collection collection = Collection.getInstance(this.getId(), this.getName(), this.getDescription());
        Set<Item> items = new HashSet<>();
        for(ItemDto itemDto : this.getItemDtos()) {
            items.add(itemDto.toItem());
        }
        collection.setItems(items);
        return collection;
    }

    public static CollectionDto toDto(Collection collection) {
        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setId(collection.getId());
        collectionDto.setName(collection.getName());
        collectionDto.setDescription(collection.getDescription());
        collectionDto.setItemDtos(ItemDto.toItemDtos(collection.getItems()));
        return collectionDto;
    }

    public static List<CollectionDto> toDtos(java.util.Collection<Collection> collections) {
        List<CollectionDto> collectionDtos = new ArrayList<>();
        if(null != collections) {
            for(Collection collection : collections) {
                collectionDtos.add(toDto(collection));
            }
        }
        return collectionDtos;
    }
}
