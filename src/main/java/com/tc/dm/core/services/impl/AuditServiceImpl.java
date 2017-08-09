package com.tc.dm.core.services.impl;

import com.tc.dm.core.entities.CustomRevisionEntity;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.AuditService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class AuditServiceImpl implements AuditService {
//Users should be able to see a history of what they have added or curated. This history should be filterable.
//They should be able to open these items for editing when necessary, and then send for curating.
//The admin should be able to see a log of the entire history of the database. This log should be saveable.
//Logs for individual items should also be maintained, including who made changes.

    @PersistenceContext
    protected EntityManager em;



    public List getAllRevInfos() {

        AuditReader auditReader = AuditReaderFactory.get(em);

        AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(Item.class, false, false);

        Object[] auditObj = (Object[]) query.getSingleResult();

        CustomRevisionEntity revisionEntity = (CustomRevisionEntity) auditObj[1];


        return new ArrayList();
    }

}
