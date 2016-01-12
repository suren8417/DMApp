package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.CollectionDaoImpl;
import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    public Collection updateCollection(final Collection collection) throws Exception {
        try {
            List<Long> commonItemIds = new ArrayList<>();
            List<Long> newItemIds = new ArrayList<>();
            List<Long> removedItemIds = new ArrayList<>();

            for(Item item : collection.getItems()) {
                    newItemIds.add(item.getId());
            }

            for(Item item : collectionDao.find(Collection.class, collection.getId()).getItems()) {
                if(!newItemIds.contains(item.getId())) {
                    removedItemIds.add(item.getId());
                } else {
                    commonItemIds.add(item.getId());
                }
            }

            Collection daoCollection = collectionDao.find(Collection.class, collection.getId());
            for(Long itemId : removedItemIds) {
                Item item = itemDao.find(Item.class, itemId);
                item.getCollections().remove(daoCollection);
                itemDao.update(item);
            }

            for (Long itemId : newItemIds) {
                if(!commonItemIds.contains(itemId)) {
                    Item daoItem = itemDao.find(Item.class, itemId);
                    daoItem.getCollections().add(daoCollection);
                    itemDao.update(daoItem);
                }
            }

            daoCollection = collectionDao.find(Collection.class, collection.getId());
            daoCollection.setName(collection.getName());
            daoCollection.setDescription(collection.getDescription());
            daoCollection = collectionDao.update(daoCollection);

            daoCollection = collectionDao.find(Collection.class, collection.getId());
            return daoCollection;
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
