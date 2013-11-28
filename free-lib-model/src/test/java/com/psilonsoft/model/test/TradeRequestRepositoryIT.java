package com.psilonsoft.model.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.TradeRequest;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.BookRepository;
import com.psilonsoft.model.repository.TradeRequestRepository;
import com.psilonsoft.model.repository.UserRepository;

public class TradeRequestRepositoryIT extends BaseModelTestIT {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeRequestRepository tradeRequestRepository;

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void canCreateAndRetrieveMessage() {
        User user1 = Helpers.prepareFullyPopulatedUser();
        User user2 = Helpers.prepareFullyPopulatedUser();
        Book book = Helpers.prepareBook(user1);

        userRepository.save(user1);
        userRepository.save(user2);
        bookRepository.save(book);

        entityManager.flush();

        TradeRequest tradeRequest = Helpers.prepareMessage(user2, user1, book);
        tradeRequestRepository.save(tradeRequest);
        entityManager.flush();

        Assert.assertEquals("Expecting message contents to be the same", tradeRequest.getContents(),
                tradeRequestRepository.findMessageById(tradeRequest.getId()).getContents());
    }

}
