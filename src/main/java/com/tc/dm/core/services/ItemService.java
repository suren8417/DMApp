package com.tc.dm.core.services;

import com.tc.dm.core.entities.Item;

import java.util.List;

public interface ItemService {

    public Item createItem(Item item);
    public void updateItem(Item item);
    public void deleteItem(Item item);
    public Item findById(Long itemId);
    public List<Item> findAll();
    public List<Item> findPageOfItems(int pageIndex, int pageSize);
}
