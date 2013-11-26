package com.psilonsoft.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Audit.Action;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.UserRepository;
import com.psilonsoft.services.AuditService;
import com.psilonsoft.services.LoginService;

/**
 * 
 * Implementation for {@link LoginService}
 * 
 * The @Service annotation indicates for spring that this is implementation of {@link LoginService}
 * interface
 * 
 * 
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuditService auditService;
    
    @Override
    @Transactional
    public User login(final String email) {
        // try to fetch user
        User user = userRepository.findByEmail(email.toLowerCase());
        // if it does not exist - return it.
        if (user == null) {
            user = new User();
            user.setCreatedOn(new Date());
            user.setEmail(email.toLowerCase());
            userRepository.save(user);
        }
        
        auditService.logAction(Action.LOGIN, user.getId());

        return user;
    }
}
