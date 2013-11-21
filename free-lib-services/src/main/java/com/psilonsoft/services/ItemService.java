package com.psilonsoft.services;

import com.psilonsoft.model.entities.Item;

public interface ItemService {

    public Iterable<Item> getAll();

    public Item getById(Long itemId);

}
