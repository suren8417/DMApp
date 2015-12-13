package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.CollectionDaoImpl;
import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionDaoImpl collectionDao;

    @Autowired
    ItemDaoImpl itemDao;

    @Override
    public Collection createCollection(Collection collection) throws Exception {
        try {
            HashSet<Item> items = new HashSet<>();
            for (Item item : collection.getItems()) {
                item = item.getId() == null ? item : itemDao.find(Item.class, item.getId());
                item.getCollections().add(collection);
                items.add(item);
            }
            collection.setItems(items);
            return collectionDao.create(collection);
        } catch (Exception e) {
            throw new Exception("Collection creation Failed", e);
        }
    }

    @Override
    public Collection updateCollection(Collection collection) throws Exception {
        try {
            Set<Item> items = collection.getItems();
            collection.setItems(null);
            collection = collectionDao.update(collection);
            for (Item item : items) {
                if (item.getId() == null) {
                    if (!item.getCollections().contains(collection)) {
                        item.getCollections().add(collection);
                    }
                    item = itemDao.create(item);
                } else {
                    item = itemDao.find(Item.class, item.getId());
                    boolean alreadyAdded = false;
                    for (Collection itemCollection : item.getCollections()) {
                        if (itemCollection.getId().equals(collection.getId())) {
                            alreadyAdded = true;
                        }
                    }
                    if (!alreadyAdded) {
                        item.getCollections().add(collection);
                        item = itemDao.update(item);
                    }
                }
            }
            collection = collectionDao.find(Collection.class, collection.getId());
            return collection;
        } catch (Exception e) {
            throw new Exception("Collection update Failed", e);
        }
    }

    @Override
    public void deleteCollection(Collection collection) throws Exception {
        try {
            collection = collectionDao.find(Collection.class, collection.getId());
            collectionDao.delete(collection.preDelete());
        } catch (Exception e) {
            throw new Exception("Collection deletion Failed", e);
        }
    }

    @Override
    public Collection findCollectionById(Long collectionId) throws Exception {
        try{
        return collectionDao.find(Collection.class, collectionId);
        } catch (Exception e) {
            throw new Exception("Loan collection by id Failed", e);
        }
    }

    public List<Collection> findAllCollections() {
        return collectionDao.findAll();
    }
}
