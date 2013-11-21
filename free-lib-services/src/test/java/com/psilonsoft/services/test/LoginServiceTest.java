package com.psilonsoft.services.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.AuditRepository;
import com.psilonsoft.model.repository.UserRepository;
import com.psilonsoft.services.AuditService;
import com.psilonsoft.services.LoginService;

/**
 * 
 * tests for {@link LoginService}
 * 
 * @author gytis
 */
public class LoginServiceTest extends BaseServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuditService auditService;

    /**
     * This is a special class - a Mock
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * This is a special class - a Mock
     */
    @Autowired
    private AuditRepository auditRepository;

    @Test
    public void loginMethodWorksWhenUserExists() {
        // Define some variables.
        String demoEmail = "demo@email.com";
        User user = new User();
        user.setEmail(demoEmail);

        // Indicate to mockito framework that we will be expecting a call to this method.
        Mockito.when(userRepository.findByEmail(demoEmail)).thenReturn(user);

        // actualy try to execute the login method
        User loggedInUser = loginService.login(demoEmail);

        // verify that data was retrieved, and it is correct
        Assert.assertEquals("Expected users to match", "demo@email.com", loggedInUser.getEmail());

        // verify that our logingService.login method actually called repository method.
        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(demoEmail);

        // TODO: this test is incomplete. How would you fix it?

    }
}
