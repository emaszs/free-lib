package com.psilonsoft.services;

import java.util.List;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.TradeRequest;
import com.psilonsoft.model.entities.User;

public interface TradeRequestService {
    public void createNewRequest(User userFrom, User userTo, Book book);

    public void deleteRequest(Long requestId);

    public List<TradeRequest> getAll();

    public TradeRequest findOne(Long requestId);
    

}
