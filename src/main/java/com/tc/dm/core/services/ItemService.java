package com.tc.dm.core.services;

import com.tc.dm.core.entities.Item;
import com.tc.dm.core.entities.ItemType;
import com.tc.dm.rest.dto.SearchParam;

import java.util.List;

public interface ItemService {

    public Item createItem(Item item);

    public void updateItem(Item item);

    public void deleteItem(Item item);

    public Item findItemById(Long itemId);

    public List<Item> findAllItems();

    public List<Item> findPageOfItems(int pageIndex, int pageSize, boolean withContent);

    public List<Item> searchItems(SearchParam searchParam);

    public ItemType createItemType(ItemType itemType);

    public void updateItemType(ItemType itemType);

    public void deleteItemType(ItemType itemType);

    public ItemType findItemTypeById(Long itemTypeId);

    public ItemType findItemTypeByName(String itemTypeName);
}
