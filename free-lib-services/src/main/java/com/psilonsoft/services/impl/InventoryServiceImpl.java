package com.psilonsoft.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.repository.ItemRepository;
import com.psilonsoft.services.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public List<Item> getAllItemsInInventory() {
        List<Item> items = new ArrayList<Item>();

        
        items = (List<Item>) itemRepository.findAll();
        return items;
    }

}
