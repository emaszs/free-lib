package com.psilonsoft.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.User;

/**
 * 
 * Database manipulation interface for {@link User} entity
 * 
 * 
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Gets user by email.
     * 
     * This method demonstrates "convention over configuration" usage. Spring automatically detectd
     * that we want to search by email field in {@link User} class. It does so by looking at method
     * name and signature.
     * 
     * The Usual way to get some entity by its property is to write methods like
     * findBy{PropertyName}.
     * 
     * Multiple properties can be combined by using And keyword - findByEmailAndUserId
     * 
     * @param email - email of user. {@link User#getEmail()}
     * @return {@link User} or null if none was found.
     */
    public User findByEmail(final String email);

}
