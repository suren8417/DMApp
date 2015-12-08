package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.CollectionDaoImpl;
import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionDaoImpl collectionDao;

    @Autowired
    ItemDaoImpl itemDao;

    @Override
    public Collection createCollection(Collection collection) {
//        HashSet<Item> items = new HashSet<>();
//        for(Item item : collection.getItems()){
//            item = item.getId()==null?item:itemDao.find(Item.class, item.getId());
//            items.add(item);
//        }
//        collection.setItems(items);
        Item itemTemp = Item.getInstance();
        itemTemp.setId(2L);
        collection.getItems().add(itemTemp);
        return collectionDao.create(collection);
    }

    @Override
    public void updateCollection(Collection collection) {
        collectionDao.update(collection);
    }

    @Override
    public void deleteCollection(Collection collection) {
        collection = collectionDao.find(Collection.class, collection.getId());
        collectionDao.delete(collection.preDelete());
    }

    @Override
    public Collection findCollectionById(Long collectionId) {
        return collectionDao.find(Collection.class, collectionId);
    }
}
