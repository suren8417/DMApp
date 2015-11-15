package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.dao.impl.ItemTypeDaoImpl;
import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.entities.ItemType;
import com.tc.dm.core.entities.KeyWord;
import com.tc.dm.core.services.FileService;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDaoImpl itemDao;

    @Autowired
    ItemTypeDaoImpl itemTypeDao;

    @Autowired
    FileService fileService;

    @Override
    public Item createItem(Item item) {
        final String contentPath = fileService.storeFile(item.getContent());
        item.setContentPath(contentPath);
        final Long typeId = item.getItemType().getId();
        final ItemType itemType = typeId!=null?itemTypeDao.find(ItemType.class, typeId):itemTypeDao.create(item.getItemType());
        item.setItemType(itemType);
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
        itemDao.delete(item);
        fileService.deleteFile(item.getContentPath());
    }

    @Override
    public Item findById(Long itemId) {
        final Item item = itemDao.find(Item.class, itemId);
        if(null != item) {
            item.setContent(fileService.getFile(item.getContentPath()));
        }
        return item;
    }

    @Override
    public ItemContent getItemContent(Long itemId) {
        return findById(itemId).getContent();
    }

    @Override
    public List<Item> findAllItems(boolean withContent) {
        List<Item> items = itemDao.findAll();
        if(withContent){
            return populateItemContent(items);
        } else {
            return items;
        }
    }

    @Override
    public List<Item> findPageOfItems(int pageIndex, int pageSize, boolean withContent) {
        List<Item> items = itemDao.findPage(pageIndex, pageSize);
        if(withContent){
            return populateItemContent(items);
        } else {
            return items;
        }
    }

    private List<Item> populateItemContent(List<Item> items) {
        if(items == null || items.isEmpty()) {
            return items;
        }
        for(Item item : items) {
            item.setContent(getItemContent(item.getId()));
        }
        return items;
    }
}
