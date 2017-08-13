package com.tc.dm.rest.dto;

import com.tc.dm.core.entities.CustomRevisionEntity;
import org.hibernate.envers.RevisionType;

import java.util.Date;

public class AuditRevisionDto {

    protected Long id;
    protected String type;
    protected String auditorName;
    protected Date auditTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditRevisionDto that = (AuditRevisionDto) o;

        if (!id.equals(that.id)) return false;
        if (!type.equals(that.type)) return false;
        if (!auditorName.equals(that.auditorName)) return false;
        return auditTime.equals(that.auditTime);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + auditorName.hashCode();
        result = 31 * result + auditTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AuditRevisionDto{" +
                "id=" + id +
                ", type=" + type +
                ", auditorName='" + auditorName + '\'' +
                ", auditTime=" + auditTime +
                '}';
    }

    public void loadRevisionEntity(CustomRevisionEntity revisionEntity, RevisionType type) {
        setId((long) revisionEntity.getId());
        setType(getRevisionTypeAsString(type));
        setAuditorName(revisionEntity.getUserName());
        setAuditTime(revisionEntity.getRevisionDate());
    }

    private String getRevisionTypeAsString(RevisionType type) {
        switch (type){
            case ADD : return "created";
            case MOD: return "modified";
            case DEL: return "deleted";
            default: throw new IllegalArgumentException("Unknown RevisionType: " + type.toString());
        }
    }
}
