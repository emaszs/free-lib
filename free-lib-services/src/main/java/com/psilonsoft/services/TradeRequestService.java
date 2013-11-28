package com.psilonsoft.services;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.User;

public interface TradeRequestService {
    public void createNewRequest(User userFrom, User userTo, Book book);
}
