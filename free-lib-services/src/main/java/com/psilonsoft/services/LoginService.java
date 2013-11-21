package com.psilonsoft.services;

import com.psilonsoft.model.entities.User;

/**
 * 
 * Login management service.
 * 
 * @author gytis
 */
public interface LoginService {
    
    /**
     * Logins in user - gets or creates user by given email.
     * 
     * @param email - email with which user should be retrieved or created if it does not exist.
     * @return {@link User} that has the given email.
     */
    public User login(final String email);

}
