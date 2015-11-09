package com.tc.dm.core.services.impl;

import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.ItemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/business-config.xml")
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;
    private Item itemA;

    @Before
    public void setUp() throws Exception {
        itemA = Item.createItem();
        itemA.setTypeId(new Long(1));
        itemA.setTitle("item1");
        itemA.setCollectionId(new Long(1));
        itemA.setDateOfOrigin(new Date());
        itemA.setDonor("Person1");
        itemA.setDescription("sample text");
        itemA.setKeyWordId(new Long(1));
        itemA.setStatus("New");
        itemA.setContentPath("/temp/img1.jpg");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateItem() throws Exception {
        Item itemANew = itemService.createItem(itemA);
        Assert.notNull(itemANew.getId());
        testFindById();
        testUpdateItem();
        testDeleteItem();
    }

    public void testUpdateItem() throws Exception {
        itemA.setDescription("Desc is edited");
        itemService.updateItem(itemA);
        Item itemANew = itemService.findById(itemA.getId());
        Assert.isTrue("Desc is edited".equals(itemANew.getDescription()));
    }

    public void testDeleteItem() throws Exception {
        Long id = itemA.getId();
        Item itemAnewForDelete = itemService.findById(id);
        itemService.deleteItem(itemAnewForDelete);
        Item itemANewAfterDelete = itemService.findById(id);
        Assert.isNull(itemANewAfterDelete);
    }

    public void testFindById() throws Exception {
        Long id = itemA.getId();
        Item itemANew = itemService.findById(id);
        Assert.notNull(itemANew);
    }

    public void testFindAll() throws Exception {

    }

    public void testFindPageOfItems() throws Exception {

    }
}