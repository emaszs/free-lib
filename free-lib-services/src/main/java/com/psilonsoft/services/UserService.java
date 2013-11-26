package com.psilonsoft.services;

import com.psilonsoft.model.entities.User;

/**
 * Exposes users service.
 * 
 * 
 */
public interface UserService {

    /**
     * Gets all users.
     * 
     * @return list of {@link User}
     */
    Iterable<User> getAll();

    /**
     * Gets user by id.
     * 
     * @param userId - id of user to get
     * @return {@link User}
     */
    User get(Long userId);

    /**
     * Updates given user.
     * 
     * @param user - user to update
     * @return updated user.
     */
    User save(User user);

}
