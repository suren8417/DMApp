package com.tc.dm.core.services;

import com.tc.dm.core.entities.Collection;

public interface CollectionService {

    public Collection createCollection(Collection collection);

    public void updateCollection(Collection collection);

    public void deleteCollection(Collection collection);

    public Collection findCollectionById(Long collectionId);

}