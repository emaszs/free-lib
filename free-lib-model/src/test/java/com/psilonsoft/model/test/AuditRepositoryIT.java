package com.psilonsoft.model.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Audit;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.entities.Audit.Action;
import com.psilonsoft.model.repository.AuditRepository;
import com.psilonsoft.model.repository.UserRepository;

/**
 * 
 * Test classes for {@link AuditRepository} class and {@link Audit} entity.
 * 
 * TODO: add more test cases.
 * 
 * @author gytis
 */
public class AuditRepositoryIT extends BaseModelTestIT {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuditRepository auditRepository;

    /**
     * Checks that association between user and audit work correclty.
     * 
     */
    @Test
    @Transactional
    public void canRetrieveAuditsForUser() {
        // create new user
        User user = Helpers.prepareFullyPopulatedUser();
        userRepository.save(user);

        // create a single audit record
        Audit audit = new Audit();
        audit.setAction(Action.LOGIN);
        audit.setDate(new Date());
        audit.setUser(user);
        auditRepository.save(audit);

        // Check if we can retrieve audits for user by his id.
        List<Audit> audits = auditRepository.findAuditsForUser(user.getId());

        // Verify assertions - we should only have single audit and the retrieved audit user id must
        // match our user id.
        Assert.assertEquals("Expected to get single audit", 1, audits.size());
        Assert.assertEquals("Expected user ids to match", user.getId(), audits.get(0).getUser().getId());

    }
}
