package com.tc.dm.core.services;

import com.tc.dm.core.entities.Collection;

import java.util.List;

public interface CollectionService {

    public Collection createCollection(Collection collection) throws Exception;

    public Collection updateCollection(Collection collection) throws Exception;

    public void deleteCollection(Collection collection) throws Exception;

    public Collection findCollectionById(Long collectionId) throws Exception;

    public List<Collection> findAllCollections();

}
