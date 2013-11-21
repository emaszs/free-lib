package com.psilonsoft.services;

import com.psilonsoft.model.entities.Audit;


public interface AuditService {
    
    public void logAction(final Audit.Action action, final Long userId);

}
