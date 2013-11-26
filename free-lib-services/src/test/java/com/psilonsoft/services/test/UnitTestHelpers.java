package com.psilonsoft.services.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.entities.User.Role;

/**
 * 
 * Helper methods for service unit tests
 * 
 * 
 */
public class UnitTestHelpers {
    public static List<Item> generateItems(final int numberOfItems) {
        List<Item> items = new ArrayList<Item>();

        for (int i = 0; i < numberOfItems; i++) {
            Item item = new Item();
            item.setName(new Date().getTime() + "");
            item.setPrice(i);
            item.setStock(3);
            item.setImage("http://www.assorti.lt/userfiles/uploader/404x303.g/selga-chocolate-sokolado-skonio-sausainiai-180g.jpg");
            items.add(item);
        }
        return items;
    }

    public static User prepareFullyPopulatedUser() {
        User user = new User();
        user.setActive(true);
        user.setCreatedOn(new Date());
        user.setRole(Role.REGULAR);
        user.setDebt(0);
        user.setEmail(new Date().getTime() + "@gmail.com");
        return user;
    }

}
