package com.tc.dm.core.services;

import com.tc.dm.core.entities.Item;
import com.tc.dm.rest.dto.ItemStatus;
import com.tc.dm.rest.dto.SearchParam;

import java.util.List;

public interface ItemService {

    public Item createItem(Item item) throws Exception;

    public Item updateItem(Item item) throws Exception;

    public void deleteItem(Item item) throws Exception;

    public Item findItemById(Long itemId) throws Exception;

    public List<Item> findAllItems();

    public List<Item> findItemsByStatus(ItemStatus... itemStatus);

    public List<Item> findPageOfItems(int pageIndex, int pageSize, boolean withContent);

    public List<Item> searchItems(SearchParam searchParam);

    public List<Item> findRecentAdditions();

    public String generateItemCode(Item item) throws Exception;
}
