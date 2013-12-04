package com.psilonsoft.model.test;

import java.util.Date;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.TradeRequest;
import com.psilonsoft.model.entities.User;

/**
 * 
 * Variuos helper methods for object population for reuse in tests.
 * 
 * 
 */
public class Helpers {

    /**
     * Creates and returns fully popupulated {@link User} object that is ready to be saved to the
     * database.
     * 
     * @return fully populated {@link User}.
     */
    public static User prepareFullyPopulatedUser() {
        User user = new User();
        user.setCreatedOn(new Date());
        user.setEmail(new Date().getTime() + "@gmail.com");
        return user;
    }


    public static TradeRequest prepareMessage(final User to, final User from, final Book book) {
        TradeRequest t = new TradeRequest();
        t.setBook(book);
        t.setUserFrom(from);
        t.setUserTo(to);
        t.setContents(new Date().getTime() + "");
        return t;
    }

    public static Book prepareBook(final User user) {
        Book b = new Book();
        b.setUser(user);
        b.setCategories("ccc");
        b.setName("Shakespeare test book");
        b.setDescription("Very good yes");
        b.setImg("http://smartmobilestudio.com/wp-content/uploads/2012/06/leather-book-preview.png");

        return b;
    }
}
