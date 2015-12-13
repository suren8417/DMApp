package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.FileService;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemStatus;
import com.tc.dm.rest.dto.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDaoImpl itemDao;

    @Autowired
    FileService fileService;

    @Override
    public Item createItem(Item item) throws Exception {
        try {
            final String contentPath = fileService.storeFile(item.getContent());
            item.setContentPath(contentPath);
            return itemDao.create(item);
        } catch (Exception e) {
            throw new Exception("Item Creation Failed:", e);
        }
    }

    @Override
    public Item updateItem(Item item) throws Exception {
        try {
            String contentPath = findItemById(item.getId()).getContentPath();
            if (item.getContent() != null) {
                fileService.deleteFile(contentPath);
                contentPath = fileService.storeFile(item.getContent());
            }
            item.setContentPath(contentPath);
            item = itemDao.update(item);
            return item;
        } catch (Exception e) {
            throw new Exception("Item Update Failed:", e);
        }
    }


    @Override
    public void deleteItem(Item item) throws Exception {
        try {
            item = itemDao.find(Item.class, item.getId());
            item.preDelete();
            final String filePath = item.getContentPath();
            itemDao.delete(item);
            fileService.deleteFile(filePath);
        } catch (Exception e) {
            throw new Exception("Item Content Deletion Failed:" + item.getContentPath(), e);
        }
    }

    @Override
    public Item findItemById(Long itemId) throws Exception {
        try {
            final Item item = itemDao.find(Item.class, itemId);
            if (null != item) {
//                item.setContent(fileService.getFile(item.getContentPath()));
            }
            return item;
        } catch (Exception e) {
            throw new Exception("Item Retrieval Failed:", e);
        }
    }


    @Override
    public List<Item> findAllItems() {
        List<Item> items = itemDao.findAll();
        return items;
    }

    public List<Item> findItemsByStatus(ItemStatus... itemStatus) {
        if(null == itemStatus) {
            return this.findAllItems();
        }
        SearchParam searchParam = new SearchParam();
        for(ItemStatus status : itemStatus) {
            searchParam.getStatus().add(status);
        }
        List<Item> items = itemDao.search(searchParam.toMap());
        return items;
    }

    @Override
    public List<Item> findPageOfItems(int pageIndex, int pageSize, boolean withContent) {
        List<Item> items = itemDao.findPage(pageIndex * pageSize - pageSize, pageSize);
        return items;
    }

    @Override
    public List<Item> searchItems(SearchParam searchParam) {
        List<Item> items = itemDao.search(searchParam.toMap());
        return items;
    }
}
