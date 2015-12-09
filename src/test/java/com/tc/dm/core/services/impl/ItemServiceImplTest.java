package com.tc.dm.core.services.impl;

import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.entities.ItemType;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemSearchParam;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/business-config.xml")
public class ItemServiceImplTest {

    public static final String[] ITEM_TYPE = {"Single photograph", "Collection of photographs", "Document",
            "Collection of documents", "Book", "Map", "Object", "Collection of Objects"};

    @Autowired
    private ItemService itemService;
    private Long itemId;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

//    @Test
//    public void testCreateItemType() {
//        ItemType itemType = ItemType.getInstance(null, ITEM_TYPE[0], "Type of JPG, PNG, JPEG and GIF image");
//        itemType = itemService.createItemType(itemType);
//        Assert.notNull(itemType.getId(), "ItemType id is not created");
//
//        final String newDesc = "File types of JPG, PNG, JPEG and GIF";
//        itemType.setDescription(newDesc);
//        itemService.updateItemType(itemType);
//        itemType = itemService.findItemTypeById(itemType.getId());
//        Assert.isTrue(newDesc.equals(itemType.getDescription()), "Item description did not updated");
//
//        itemType = null;
//        itemType = itemService.findItemTypeByName(ITEM_TYPE[0]);
//        Assert.notNull(itemType, "Find by name returns nothing");
//
//        Long itemTypeId = itemType.getId();
//        itemService.deleteItemType(itemType);
//        itemType = itemService.findItemTypeById(itemTypeId);
//        Assert.isNull(itemType, "Deleted ItemType exist");
//    }

    @Test
    public void testCreateItem() throws Exception {
        final int x = new Random().nextInt();
        Item itemA = Item.getInstance();
        //ItemType itemTypeA = ItemType.getInstance(1L, "TestItemType1610193648", "TestItemDesc1610193648");
        itemA.setType(ITEM_TYPE[0]);
        //ItemType itemTypeA = ItemType.getInstance("TestItemType" + x, "TestItemDesc" + x);
        //HashSet<Item> itemSet = new HashSet<Item>();
        //itemSet.add(itemA);
        //itemTypeA.setItems(itemSet);
        //itemA.setItemType(ItemType.getInstance("TestItemType" + x, "TestItemDesc" + x));
        itemA.setTitle("ItemTitleTest"+x);
        itemA.setCollections(new HashSet<Collection>(){{add(Collection.getInstance(null, "TestCollectionName"+x, "TestCollectionDesc"+x));}});
        itemA.getCollections().add(Collection.getInstance(1L, null, null));
        itemA.setDateOfOrigin(new Date());
        itemA.setDateAdded(new Date());
        itemA.setDonor("TestDonorPerson"+x);
        itemA.setDescription("TestItemDescText"+x);
        itemA.setKeywords("test photo");
        itemA.setStatus("TestStatus"+x);
        itemA.setContentPath("/temp/img"+x+".jpg");
        itemA = itemService.createItem(itemA);
        Assert.notNull(itemA.getId(), "Item id is null hence item not created");
        this.itemId = itemA.getId();
        testFindById();
        testUpdateItem();
        testFindAll();
        testDeleteItem();
    }

    public void testUpdateItem() throws Exception {
        Item itemA = itemService.findItemById(itemId);
        final String newDesc = "Desc is edited";
        itemA.setDescription(newDesc);
        itemService.updateItem(itemA);
        itemA = itemService.findItemById(itemId);
        Assert.isTrue(newDesc.equals(itemA.getDescription()));
    }

    public void testDeleteItem() throws Exception {
        Item itemA = itemService.findItemById(itemId);
        Set<Collection> collections = itemA.getCollections();
        itemService.deleteItem(itemA);
        itemA = itemService.findItemById(itemId);
        Assert.isNull(itemA, "Item should be null after deletion");
    }

    public void testFindById() throws Exception {
        Item itemANew = itemService.findItemById(this.itemId);
        Assert.notNull(itemANew);
    }

    public void testFindAll() throws Exception {
        List<Item> itemList = itemService.findAllItems();
        boolean itemAFound = false;
        for (Item item : itemList){
            if(item.getId().equals(itemId)){
                itemAFound = true;
                break;
            }
        }
        Assert.isTrue(itemAFound, "Created Item not returned in findAll");
    }

    public void testFindPageOfItems() throws Exception {

    }

    @Test
    public void testItemSearch() {
        ItemSearchParam param = new ItemSearchParam();
        param.setTextToSearch("Detached");
        List<Item> itemList = itemService.searchItems(param);
        Assert.isTrue(itemList.get(0).getTitle().contains("Detached"), "Test not contain search term");
    }
}