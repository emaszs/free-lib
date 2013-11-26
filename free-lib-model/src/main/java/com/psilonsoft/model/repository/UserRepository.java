package com.psilonsoft.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
    
    /**
     * Gets all users by given role.
     * 
     * This method demonstrates use of JPQL (Java Persistence API Query Language)
     * 
     * The "?1" indicates that we should take first argument from method parameters.
     * 
     * @param role - role for which we want to retrieve users.
     * @return list of {@link User} with matched role, or empty list if none were found.
     */
    @Query("select u from User u where u.role=?1")
    public List<User> findByRole(final User.Role role);

    /**
     * Gets All inactive users.
     * 
     * This methods demonstrates how to use plain old SQL.
     * 
     * @return list of {@link Users} that ar not active
     */
    @Query(nativeQuery = true, value = "select * from user where active=0")
    public List<User> findNonActive();

    // @Modifying
    // @Query("UPDATE User u SET u.debt = ?2 WHERE u = ?1")
    // public void updateUserDebt(User user, int debt);
}
