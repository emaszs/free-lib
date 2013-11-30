package com.psilonsoft.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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

    @Override
    @Transactional
    public List<TradeRequest> getAll() {
        // TODO Auto-generated method stub
        return (List<TradeRequest>) tradeRequestRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteRequest(final Long requestId) {
        // TODO Auto-generated method stub
        tradeRequestRepository.delete(requestId);

    }

    @Override
    @Transactional
    public TradeRequest findOne(final Long requestId) {
        // TODO Auto-generated method stub
        return tradeRequestRepository.findOne(requestId);
    }

}
