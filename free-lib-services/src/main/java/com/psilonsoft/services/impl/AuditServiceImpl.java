package com.psilonsoft.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Audit;
import com.psilonsoft.model.entities.Audit.Action;
import com.psilonsoft.model.repository.AuditRepository;
import com.psilonsoft.model.repository.UserRepository;
import com.psilonsoft.services.AuditService;

@Service
public class AuditServiceImpl implements AuditService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditRepository auditRepository;

    @Override
    @Transactional
    public void logAction(final Action action, final Long userId) {

        Audit audit = new Audit();
        audit.setAction(action);
        audit.setDate(new Date());
        audit.setUser(userRepository.findOne(userId));
        auditRepository.save(audit);
    }

}
