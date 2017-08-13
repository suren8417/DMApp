package com.tc.dm.core.services;


import com.tc.dm.rest.dto.CollectionDto;
import com.tc.dm.rest.dto.ItemDto;
import com.tc.dm.rest.dto.UserAuditDto;
import org.hibernate.envers.RevisionType;

import java.util.Date;
import java.util.List;

public interface AuditService {

    List<ItemDto> getItemAuditInfo(Long itemId, String auditorName, RevisionType revisionType, Date from, Date to);

    List<CollectionDto> getCollectionAuditInfo(Long collectionId, String auditorName, RevisionType revisionType, Date from, Date to);

    UserAuditDto getUserAuditInfo(String userName);
}
