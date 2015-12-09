package com.tc.dm.rest.dto;

import com.tc.dm.core.entities.Item;
import com.tc.dm.core.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sg40304 on 12/8/15.
 */
public class ItemDto {

    private Long id;
    private String itemsSelectedType;
    private String itemTitle;
    private String itemDonor;
    private String itemDescription;
    private String itemKeyWords;
    private Date itemStartDate;
    private MultipartFile uploadItem;
    private String status;
    private String itemName;

    public Long getId() {
        return id;
    }

    public String getItemsSelectedType() {
        return itemsSelectedType;
    }

    public String getItemDonor() {
        return itemDonor;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemKeyWords() {
        return itemKeyWords;
    }

    public Date getItemStartDate() {
        return itemStartDate;
    }

    public MultipartFile getUploadItem() {
        return uploadItem;
    }


    public void setItemsSelectedType(String itemsSelectedType) {
        this.itemsSelectedType = itemsSelectedType;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemDonor(String itemDonor) {
        this.itemDonor = itemDonor;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemKeyWords(String itemKeyWords) {
        this.itemKeyWords = itemKeyWords;
    }

    public void setItemStartDate(Date itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public void setUploadItem(MultipartFile uploadItem) {
        this.uploadItem = uploadItem;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Item toItem() {
        Item item = Item.getInstance();
        item.setId(id);
        item.setType(itemsSelectedType);
        item.setTitle(itemTitle);
        item.setDonor(itemDonor);
        item.setDescription(itemDescription);
        item.setKeywords(itemKeyWords);
        item.setDateOfOrigin(itemStartDate);
        item.setContent(uploadItem);
        item.setStatus("INITIAL");
        return item;

    }

    public List<ItemDto> toItemDtos(List<Item> items) {

        List<ItemDto> list = new ArrayList<ItemDto>();
        for (Item item : items) {
            if ("INITIAL".equals(item.getStatus())) {
                ItemDto itemDto = new ItemDto();
                itemDto.setId(item.getId());
                itemDto.setItemTitle(item.getTitle());
                itemDto.setStatus(item.getStatus());
                itemDto.setItemsSelectedType(item.getType());
                itemDto.setItemDonor(item.getDonor());
                itemDto.setItemDescription(item.getDescription());
                itemDto.setItemKeyWords(item.getKeywords());
                itemDto.setItemStartDate(item.getDateOfOrigin());
                itemDto.setItemName(createFileName(item.getContentPath()));
                list.add(itemDto);
            }
        }
        return list;

    }

    private String createFileName(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }
}
