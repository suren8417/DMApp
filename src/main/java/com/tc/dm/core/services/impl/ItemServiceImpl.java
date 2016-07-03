package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.FileService;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemStatus;
import com.tc.dm.rest.dto.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional(rollbackFor = Exception.class)
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${recent.addition.item.count}")
    private String recentAdditionItemCount;

    @Value("${item.code.template}")
    private String itemCodeTemplate;

    @Autowired
    ItemDaoImpl itemDao;

    @Autowired
    FileService fileService;

    public int getRecentAdditionItemCount() throws NumberFormatException {
        return Integer.parseInt(this.recentAdditionItemCount);
    }

    public String getItemCodeTemplate() {
        return StringUtils.isEmpty(this.itemCodeTemplate)?"ITEM_${id}_${dateAdded}":this.itemCodeTemplate;
    }

    @Override
    public Item createItem(Item item) throws Exception {
        try {
            final String contentPath = fileService.storeFile(item.getContent());
            item.setContentPath(contentPath);
            fileService.copyToCache(contentPath);
            item.setDateAdded(new Date());
            item.setAddedBy("admin");
            item.setItemCode("ITEM_"+ UUID.randomUUID()); //generateItemCode(item));
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
        List<Item> items = itemDao.findPage(pageIndex * pageSize - pageSize, pageSize, null);
        return items;
    }

    @Override
    public List<Item> searchItems(SearchParam searchParam) {
        List<Item> items = itemDao.search(searchParam.toMap());
        return items;
    }

    @Override
    public List<Item> findRecentAdditions(int noOfItems) {
        return itemDao.findPage(0, getRecentAdditionItemCount(), "desc");
    }

    @Override
    public String generateItemCode(Item item) throws Exception {
        StringBuilder itemCode = new StringBuilder();
        String[] codes = this.getItemCodeTemplate().split("_");
        for (String code : codes) {
            if (code.startsWith("${")) {
                itemCode.append(processToken(code, item));
            }else{
                itemCode.append(code);
            }
        }
        return itemCode.toString();
    }

    private String processToken(String code, Item item) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        String processedCode = null;
        try {
            code = code.substring(2, code.length()-1);
            Field field = item.getClass().getDeclaredField(code);
            field.setAccessible(true);
            Object value = field.get(item);
            if(null == value) {
                throw new Exception("ItemCode Template contains property:"+code+" with null");
            } else if(field.getType().isAssignableFrom(String.class)){
                processedCode = String.valueOf(value).toUpperCase();
            } else if(field.getType().isAssignableFrom(Date.class)){
                processedCode = sdf.format((Date)value);
            }
        } catch (NoSuchFieldException e) {
            throw new Exception("ItemCode Template contains invalid property name:"+this.getItemCodeTemplate(), e);
        }
        return processedCode;
    }
}
