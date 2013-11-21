package com.psilonsoft.services.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.repository.ItemRepository;
import com.psilonsoft.services.InventoryService;

public class InventoryServiceTest extends BaseServiceTest {
    @Autowired
    private InventoryService inventoryService;

    /**
     * Mock
     */
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void wholeInventoryIsCorrectlyRetrieved() {
        List<Item> items = new ArrayList<>();
        items = UnitTestHelpers.generateItems(5);

        Mockito.when(itemRepository.findAll()).thenReturn(items);

        List<Item> retrievedItems = new ArrayList<>();
        retrievedItems = inventoryService.getAllItemsInInventory();

        for (int i = 0; i < 5; i++) {
            Assert.assertNotNull("Expected item to be created", retrievedItems.get(i));
            Assert.assertEquals("Amount of items should be the same", items.size(),
                    retrievedItems.size());
            Assert.assertEquals("Item names should match", items.get(i), retrievedItems.get(i));
        }

    }

}
