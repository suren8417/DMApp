package com.tc.dm.rest.dto;

import java.util.Date;

/**
 * Created by suren on 16/08/17.
 */
public class AuditResponseDto {

    private String type;
    private String operation;
    private String auditorName;
    private String  auditTime;
    private String name;

    public void setType(String type) {
        this.type = type;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public String getType() {
        return type;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
