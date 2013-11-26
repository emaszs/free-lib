package com.psilonsoft.model.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.UserRepository;

/**
 * 
 * Test classes for {@link UserRepository} class and {@link User} entity.
 * 
 * 
 */
public class UserRepositoryIT extends BaseModelTestIT {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Simply checks if we can create user.
     */
    @Test
    @Transactional
    public void canCreateAndRetrieveUser() {
        User user = Helpers.prepareFullyPopulatedUser();

        userRepository.save(user);

        User retrieved = userRepository.findOne(user.getId());

        Assert.assertNotNull("Expected that user will be created", retrieved);
        Assert.assertEquals("Expected user ids to match", user.getId(), retrieved.getId());
        Assert.assertEquals("User emails should match", user.getEmail(), retrieved.getEmail());
    }

    /**
     * Checks if we cannot create user without required fields.
     * 
     * This is another form of expectation - we indicate that we expect that this method will throw
     * ConstraintViolationException class.
     */
    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    @Transactional
    public void cannotCreateUserWithEmptyOrNullFields() {
        userRepository.save(new User());
        entityManager.flush();
    }

    /**
     * Checks if our custom dao method findByEmail works correctly.
     */
    @Transactional
    @Test
    public void userCanBeRetrievedByEmail() {
        // create user
        User user = Helpers.prepareFullyPopulatedUser();
        userRepository.save(user);

        // try to retrieve it by email
        User retrieved = userRepository.findByEmail(user.getEmail());

        // verify that everything was ok.
        Assert.assertNotNull("Expected to find user with email", retrieved);
        Assert.assertEquals("Expected user ids to match", user.getId(), retrieved.getId());
    }

    @Transactional
    @Test
    public void userDebtCanBeUpdated() {
        User user = new User();
        user = Helpers.prepareFullyPopulatedUser();

        // user.setDebt(0);
        userRepository.save(user);
        entityManager.flush();

        // user.setDebt(1);
        entityManager.flush();

        User retrieved = userRepository.findOne(user.getId());

        // Assert.assertEquals("Expecting debt to change", 1,
        // retrieved.getDebt());
    }
}
