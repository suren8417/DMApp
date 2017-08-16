package com.tc.dm.core.services.impl;

import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.CustomRevisionEntity;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.AuditService;
import com.tc.dm.rest.dto.CollectionDto;
import com.tc.dm.rest.dto.ItemDto;
import com.tc.dm.rest.dto.UserAuditDto;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class AuditServiceImpl implements AuditService {

    @PersistenceContext
    protected EntityManager em;



    public List<ItemDto> getItemAuditInfo(Long itemId, String auditorName, RevisionType revisionType, Date from, Date to) {
        List<ItemDto> itemAudits = new ArrayList<>();
        AuditReader reader = AuditReaderFactory.get(em);
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Item.class, false, true);
        addFilters(query, itemId, auditorName, revisionType, from, to);
        itemAudits.addAll(processItemAuditQueryResult(query.getResultList()));
        return itemAudits;
    }

    public  List<CollectionDto> getCollectionAuditInfo(Long collectionId, String auditorName, RevisionType revisionType, Date from, Date to) {
        List<CollectionDto> collectionAudits = new ArrayList<>();
        AuditReader reader = AuditReaderFactory.get(em);
        AuditQuery query = reader.createQuery().forRevisionsOfEntity(Collection.class, false, true);
        addFilters(query, collectionId, auditorName, revisionType, from, to);
        collectionAudits.addAll(processCollectionAuditQueryResult(query.getResultList()));
        return collectionAudits;
    }

    public UserAuditDto getUserAuditInfo(String userName, RevisionType revisionType, Date from, Date to) {
        UserAuditDto userAudits = UserAuditDto.getInstance();
        userAudits.getCollectionDtos().addAll(getCollectionAuditInfo(null, userName, revisionType, from, to));
        userAudits.getItemDtos().addAll(getItemAuditInfo(null, userName, revisionType, from, to));
        return userAudits;
    }

    private List<CollectionDto> processCollectionAuditQueryResult(List result) {
        List<CollectionDto> collectionAudits = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Object[] auditArray = (Object[]) result.get(i);
            Collection collection = (Collection) auditArray[0];
            CustomRevisionEntity revisionInfo = (CustomRevisionEntity) auditArray[1];
            RevisionType revisionType = (RevisionType) auditArray[2];
            CollectionDto collectionDto = CollectionDto.toDto(collection);
            collectionDto.getAuditInfo().loadRevisionEntity(revisionInfo, revisionType);
            collectionAudits.add(collectionDto);
        }
        return collectionAudits;
    }

    private List<ItemDto> processItemAuditQueryResult(List result) {
        List<ItemDto> itemAudits = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Object[] auditArray = (Object[]) result.get(i);
            Item item = (Item) auditArray[0];
            CustomRevisionEntity revisionInfo = (CustomRevisionEntity) auditArray[1];
            RevisionType revisionType = (RevisionType) auditArray[2];
            ItemDto itemDto = ItemDto.toDto(item);
            itemDto.getAuditInfo().loadRevisionEntity(revisionInfo, revisionType);
            itemAudits.add(itemDto);
        }
        return itemAudits;
    }

    private void addFilters(AuditQuery query, Long entityId, String auditorName, RevisionType revisionType, Date from, Date to) {
        if(query != null) {
            if(null != entityId) query.add(AuditEntity.property("id").eq(entityId));
            if(null != revisionType) query.add(AuditEntity.revisionType().eq(revisionType));
            if(null != from) query.add(AuditEntity.revisionProperty("timestamp").gt(from.getTime()));
            if(null != to) query.add(AuditEntity.revisionProperty("timestamp").lt(to.getTime()));
            if(null != auditorName) query.add(AuditEntity.revisionProperty("userName").eq(auditorName));
            query.addOrder(AuditEntity.revisionNumber().desc());
        }
    }
}
