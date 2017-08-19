package com.tc.dm.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class AuditResponseDto {

    private List<AuditCollectionDto> auditCollectionDtos = new ArrayList<>();
    private List<AuditItemDto> auditItemDtos = new ArrayList<>();

    public List<AuditCollectionDto> getAuditCollectionDtos() {
        return auditCollectionDtos;
    }

    public List<AuditItemDto> getAuditItemDtos() {
        return auditItemDtos;
    }

    public void setAuditCollectionDtos(List<AuditCollectionDto> auditCollectionDtos) {
        this.auditCollectionDtos = auditCollectionDtos;
    }

    public void setAuditItemDtos(List<AuditItemDto> auditItemDtos) {
        this.auditItemDtos = auditItemDtos;
    }
}
