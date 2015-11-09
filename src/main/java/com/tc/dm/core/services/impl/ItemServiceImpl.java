package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDaoImpl itemDao;

    @Override
    public Item createItem(Item item) {
        return itemDao.create(item);
    }

    @Override
    public void updateItem(Item item) {
        itemDao.update(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemDao.delete(item);
    }

    @Override
    public Item findById(Long itemId) {
        return itemDao.find(Item.class, itemId);
    }

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public List<Item> findPageOfItems(int pageIndex, int pageSize) {
        return itemDao.findPage(pageIndex, pageSize);
    }
}
