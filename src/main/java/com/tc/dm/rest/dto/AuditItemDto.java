package com.tc.dm.rest.dto;

import java.util.Date;

public class AuditItemDto {

    private String itemCode;
    private String type;
    private String title;
    private String dateOfOrigin;
    private String dateAdded;
    private String addedBy;
    private String dateValidated;
    private String validatedBy;
    private String donor;
    private String description;
    private String keywords;
    private String note;
    private String auditorName;
    private String  auditTime;
    private String operation;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getValidatedBy() {
        return validatedBy;
    }

    public void setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
    }



    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getDateOfOrigin() {
        return dateOfOrigin;
    }

    public void setDateOfOrigin(String dateOfOrigin) {
        this.dateOfOrigin = dateOfOrigin;
    }

    public String getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(String dateValidated) {
        this.dateValidated = dateValidated;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
