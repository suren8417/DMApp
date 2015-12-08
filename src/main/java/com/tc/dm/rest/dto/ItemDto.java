package com.tc.dm.rest.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

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
    /*private MultipartFile uploadItem;*/

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

/*
    public MultipartFile getUploadItem() {
        return uploadItem;
    }
*/

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

  /*  public void setUploadItem(MultipartFile uploadItem) {
        this.uploadItem = uploadItem;
    }
*/
    public void setId(Long id) {
        this.id = id;
    }
}
