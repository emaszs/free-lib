package com.psilonsoft.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.TradeRequest;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.TradeRequestRepository;
import com.psilonsoft.services.TradeRequestService;

@Service
public class TradeRequestServiceImpl implements TradeRequestService {
    @Autowired
    TradeRequestRepository tradeRequestRepository;

    @Override
    public void createNewRequest(final User userFrom, final User userTo, final Book book) {
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setBook(book);
        tradeRequest.setUserFrom(userFrom);
        tradeRequest.setUserTo(userTo);
        // TODO take care of tradeRequest contents - either implement functionality for user to
        // enter comments about trade, or remove them
        tradeRequest.setContents("void");

        tradeRequestRepository.save(tradeRequest);
    }

}
