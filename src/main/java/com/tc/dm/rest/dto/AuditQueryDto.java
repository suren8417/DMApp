package com.tc.dm.rest.dto;

import java.util.Date;

/**
 * Created by suren on 13/08/17.
 */
public class AuditQueryDto {

    private String item;
    private String collection;
    private  String userName;
    private Date startDate;
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItem() {
        return item;
    }

    public String getCollection() {
        return collection;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
