package com.psilonsoft.model.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.BookRepository;
import com.psilonsoft.model.repository.UserRepository;

public class BookRepositoryIT extends BaseModelTestIT {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void bookCanBeDeleted() {
        User user = Helpers.prepareFullyPopulatedUser();
        Book book = Helpers.prepareBook(user);

        userRepository.save(user);
        bookRepository.save(book);
        entityManager.flush();

        bookRepository.delete(book);
        entityManager.flush();

        Assert.assertEquals("Expecting book to not exist", null,
                bookRepository.findOne(book.getId()));
    }

    @Test
    public void bookCanBeCreatedAndRetrieved() {
        User user = Helpers.prepareFullyPopulatedUser();
        Book book = Helpers.prepareBook(user);

        userRepository.save(user);
        bookRepository.save(book);
        entityManager.flush();

        Assert.assertEquals("Expecting books to be the same", book,
                bookRepository.findOne(book.getId()));
    }
}
