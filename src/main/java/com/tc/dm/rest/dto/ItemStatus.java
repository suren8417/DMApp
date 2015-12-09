package com.tc.dm.rest.dto;

public enum ItemStatus {

    NEW("NEW"), APPROVED("APPROVED"), PENDING("PENDING"), REMOVED("REMOVED");

    private String status;

    ItemStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }

    public static ItemStatus fromString(String status){
        if(NEW.toString().equals(status)) {
            return NEW;
        } else if(APPROVED.toString().equals(status)) {
            return APPROVED;
        } else if(PENDING.toString().equals(status)) {
            return PENDING;
        } else if(REMOVED.toString().equals(status)) {
            return REMOVED;
        } else {
            return null;
        }
    }

}