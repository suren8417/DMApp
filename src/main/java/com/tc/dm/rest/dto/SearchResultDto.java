package com.tc.dm.rest.dto;

import java.util.List;

/**
 * Created by sg40304 on 12/9/15.
 */
public class SearchResultDto {

    private String title;
    private int id;
    private String description;
    private String type;
    private List<ItemDto> itemDtos;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
