package com.psilonsoft.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.repository.ItemRepository;
import com.psilonsoft.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    
    @Override
    @Transactional
    public Iterable<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    @Transactional
    public Item getById(final Long id) {
        return itemRepository.findOne(id);
    }
}
