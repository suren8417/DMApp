package com.tc.dm.rest.dto;

import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.tc.dm.core.util.CommonUtil.extractFileName;
import static com.tc.dm.rest.dto.ItemStatus.NEW;

public class ItemDto {

    private Long id;
    private String itemCode;
    private String itemsSelectedType;
    private String itemTitle;
    private String itemDonor;
    private String itemDescription;
    private String itemKeyWords;
    private Date itemStartDate;
    private MultipartFile uploadItem;
    private String status;
    private String itemName;
    private Date addedDate;
    private String addedBy;
    private Date validatedDate;
    private String validatedBy;
    private List<Long> selectedCollection = new ArrayList<>();

    public String getItemContentPath() {
        return itemContentPath;
    }

    public void setItemContentPath(String itemContentPath) {
        this.itemContentPath = itemContentPath;
    }

    private String itemContentPath;

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

    public List<Long> getSelectedCollection() {
        return selectedCollection;
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

    public String getItemCode() {
        return  itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getValidatedDate() {
        return validatedDate;
    }

    public void setValidatedDate(Date validatedDate) {
        this.validatedDate = validatedDate;
    }

    public String getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
    }

    public void setSelectedCollection(List<Long> selectedCollection) {
        this.selectedCollection = selectedCollection;
    }

    public Item toItem() {
        Item item = Item.getInstance();
        item.setId(id);
        item.setItemCode(itemCode);
        item.setType(ItemType.fromString(itemsSelectedType)==null?null:ItemType.fromString(itemsSelectedType).toString());
        item.setTitle(itemTitle);
        item.setDonor(itemDonor);
        item.setDescription(itemDescription);
        item.setKeywords(itemKeyWords);
        item.setDateOfOrigin(itemStartDate);
        item.setContent(uploadItem);
        item.setStatus(ItemStatus.fromString(status)==null?null:ItemStatus.fromString(status).toString());
        item.setContentPath(itemContentPath);
        item.setDateAdded(addedDate);
        item.setAddedBy(addedBy);
        item.setDateValidated(validatedDate);
        item.setValidatedBy(validatedBy);
        return item;

    }

    public static ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setItemCode(item.getItemCode());
        itemDto.setItemTitle(item.getTitle());
        itemDto.setStatus(item.getStatus());
        itemDto.setItemsSelectedType(item.getType());
        itemDto.setItemDonor(item.getDonor());
        itemDto.setItemDescription(item.getDescription());
        itemDto.setItemKeyWords(item.getKeywords());
        itemDto.setItemStartDate(item.getDateOfOrigin());
        itemDto.setItemName(extractFileName(item.getContentPath()));
        itemDto.setItemContentPath(item.getContentPath());
        itemDto.setAddedDate(item.getDateAdded());
        itemDto.setAddedBy(item.getAddedBy());
        itemDto.setValidatedDate(item.getDateValidated());
        itemDto.setValidatedBy(item.getValidatedBy());
        item.getCollections();
  /*      for(Collection collection : item.getCollections()){
               itemDto.getSelectedCollection().add(collection.getId());
        }*/
        itemDto.getSelectedCollection().add(Long.parseLong("7"));
        itemDto.getSelectedCollection().add(Long.parseLong("8"));
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
