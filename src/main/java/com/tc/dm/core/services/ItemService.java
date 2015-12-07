package com.tc.dm.core.services;

import com.tc.dm.core.entities.Item;
import com.tc.dm.rest.dto.ItemContent;

import java.util.List;

public interface ItemService {

    public Item createItem(Item item);
    public void updateItem(Item item);
    public void deleteItem(Item item);
    public Item findById(Long itemId);
    public ItemContent getItemContent(Long itemId);
    public List<Item> findAllItems(boolean withContent);
    public List<Item> findPageOfItems(int pageIndex, int pageSize, boolean withContent);
}
