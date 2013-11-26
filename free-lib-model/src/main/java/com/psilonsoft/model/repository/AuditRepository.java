package com.psilonsoft.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Audit;
import com.psilonsoft.model.entities.User;

/**
 * 
 * Database manipulation interface for {@link Audit} entity
 * 
 * 
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface AuditRepository extends CrudRepository<Audit, Long> {
    
    /**
     * Get all audit trails for given user id.
     * 
     * This method demonstrates use of JPQL (Java Persistence API Query Language)
     * 
     * @param userId - id of {@link User}
     * @return a list of {@link Audit} trails for given user, or empty list if none where found.
     */
    @Query("select a from Audit a where a.user.id = ?1")
    public List<Audit> findAuditsForUser(final Long userId);

}
