package com.psilonsoft.services;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.services.exceptions.NoItemsLeftException;


/**
 * Allows user to make transactions, or rather, mark specific items as taken.
 * US04
 * 
 * @author Emilis
 */
public interface TradeService {
    public void logTakenItem(Item item, User user) throws NoItemsLeftException;
}
