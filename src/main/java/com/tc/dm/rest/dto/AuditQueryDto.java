package com.tc.dm.rest.dto;

import java.util.Date;

/**
 * Created by suren on 13/08/17.
 */
public class AuditQueryDto {

    private String name;
    private  String userName;
    private Date startDate;
    private Date endDate;

    public String getName() {
        return name;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setName(String name) {
        this.name = name;
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
}
