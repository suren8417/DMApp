package com.tc.dm.rest.dto;

import com.tc.dm.core.entities.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tc.dm.core.util.CommonUtil.extractFileName;
import static com.tc.dm.rest.dto.ItemStatus.NEW;

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
        item.setStatus(NEW.toString());
        return item;

    }

    public static ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setItemTitle(item.getTitle());
        itemDto.setStatus(item.getStatus());
        itemDto.setItemsSelectedType(item.getType());
        itemDto.setItemDonor(item.getDonor());
        itemDto.setItemDescription(item.getDescription());
        itemDto.setItemKeyWords(item.getKeywords());
        itemDto.setItemStartDate(item.getDateOfOrigin());
        itemDto.setItemName(extractFileName(item.getContentPath()));
        return itemDto;
    }

    public static List<ItemDto> toItemDtos(java.util.Collection<Item> items) {
        List<ItemDto> itemDtos = new ArrayList<ItemDto>();
        if (null != items) {
            for (Item item : items) {
                itemDtos.add(toDto(item));
            }
        }
        return itemDtos;
    }


}
