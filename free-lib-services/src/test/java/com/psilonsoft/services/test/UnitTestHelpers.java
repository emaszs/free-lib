package com.psilonsoft.services.test;

import java.util.Date;

import com.psilonsoft.model.entities.User;

/**
 * 
 * Helper methods for service unit tests
 * 
 * 
 */
public class UnitTestHelpers {

    public static User prepareFullyPopulatedUser() {
        User user = new User();
        user.setCreatedOn(new Date());
        user.setEmail(new Date().getTime() + "@gmail.com");
        return user;
    }

}
