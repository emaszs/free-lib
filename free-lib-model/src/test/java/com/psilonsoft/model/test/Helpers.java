package com.psilonsoft.model.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.Item;
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

    public static List<Item> generateItems(final int numberOfItems) {
        List<Item> items = new ArrayList<Item>();

        for (int i = 0; i < numberOfItems; i++) {
            Item item = new Item();
            item.setName(new Date().getTime() + "");
            item.setPrice(1);
            item.setStock(3);
            item.setImage("http://www.assorti.lt/userfiles/uploader/404x303.g/selga-chocolate-sokolado-skonio-sausainiai-180g.jpg");

            items.add(item);
        }
        return items;
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
