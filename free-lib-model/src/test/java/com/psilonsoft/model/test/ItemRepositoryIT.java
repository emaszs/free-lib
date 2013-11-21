package com.psilonsoft.model.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.repository.ItemRepository;

/**
 * 
 * @author Emilis
 */
public class ItemRepositoryIT extends BaseModelTestIT {


    @Autowired
    private ItemRepository itemRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void canInsertAndRetrieveItem() {
        Item item = new Item();
        item = Helpers.generateItems(1).get(0);

        itemRepository.save(item);

        Item retrieved = itemRepository.findOne(item.getId());
        Assert.assertNotNull("Expected that item will be created", retrieved);
        Assert.assertEquals("Expected item ids to match", item.getId(), retrieved.getId());
        Assert.assertEquals("Item names should match", item.getName(), retrieved.getName());
        Assert.assertEquals("Expected that item prices should match", item.getPrice(),
                retrieved.getPrice());
        Assert.assertEquals("Item stock should be the same", item.getStock(), retrieved.getStock());
    }

    @Test
    @Transactional
    public void canDecrementItemStock() {
        Item item = new Item();
        item = Helpers.generateItems(1).get(0);
        itemRepository.save(item);
        entityManager.flush();

        item.setStock(item.getStock() - 1);
        entityManager.flush();

        Item retrievedItem = new Item();
        retrievedItem = itemRepository.findOne(item.getId());

        Assert.assertEquals("Expecting stock to be lowered by 1", item.getStock(),
                retrievedItem.getStock());
    }

}
