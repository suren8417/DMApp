package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.dao.impl.ItemKeyWordDaoImpl;
import com.tc.dm.core.dao.impl.ItemTypeDaoImpl;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.entities.ItemType;
import com.tc.dm.core.services.FileService;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDaoImpl itemDao;

    @Autowired
    ItemTypeDaoImpl itemTypeDao;

    @Autowired
    ItemKeyWordDaoImpl keyWordDao;

    @Autowired
    FileService fileService;

    @Override
    public Item createItem(Item item) {
        final String contentPath = fileService.storeFile(item.getContent());
        item.setContentPath(contentPath);
        return itemDao.create(item);
    }

    @Override
    public void updateItem(Item item) {
        itemDao.update(item);
    }

    @Override
    public void deleteItem(Item item) {
        item = itemDao.find(Item.class, item.getId());
        item.preDelete();
        final String filePath = item.getContentPath();
        itemDao.delete(item);
        fileService.deleteFile(filePath);
    }

    @Override
    public Item findItemById(Long itemId) {
        final Item item = itemDao.find(Item.class, itemId);
        if(null != item) {
            item.setContent(fileService.getFile(item.getContentPath()));
        }
        return item;
    }


    @Override
    public List<Item> findAllItems() {
        List<Item> items = itemDao.findAll();
        return items;

    }

    @Override
    public List<Item> findPageOfItems(int pageIndex, int pageSize, boolean withContent) {
        List<Item> items = itemDao.findPage(pageIndex*pageSize-pageSize, pageSize);
        return items;
    }

    @Override
    public ItemType createItemType(ItemType itemType) {
        return itemTypeDao.create(itemType);
    }

    @Override
    public void updateItemType(ItemType itemType) {
        itemTypeDao.update(itemType);
    }

    @Override
    public void deleteItemType(ItemType itemType) {
        itemTypeDao.delete(itemTypeDao.find(ItemType.class, itemType.getId()));
    }

    @Override
    public ItemType findItemTypeById(Long itemTypeId) {
        return itemTypeDao.find(ItemType.class, itemTypeId);
    }

    @Override
    public ItemType findItemTypeByName(String itemTypeName) {
        for(ItemType itemType : itemTypeDao.findAll()) {
            if(itemType.getName().equals(itemTypeName)) {
                return itemType;
            }
        }
        return null;
    }

}
