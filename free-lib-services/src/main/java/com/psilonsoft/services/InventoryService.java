package com.psilonsoft.services;

import java.util.List;

import com.psilonsoft.model.entities.Item;

/**
 * Allows the user to see current inventory
 * US03
 * 
 * @author Emilis
 */
public interface InventoryService {
    
    public List<Item> getAllItemsInInventory();
}
