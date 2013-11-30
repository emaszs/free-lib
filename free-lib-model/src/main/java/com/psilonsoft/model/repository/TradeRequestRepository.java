package com.psilonsoft.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.TradeRequest;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface TradeRequestRepository extends CrudRepository<TradeRequest, Long> {

    public TradeRequest findRequestById(final Long id);
    
    @Modifying
    @Query("delete from TradeRequest t where t.book.id = ?1")
    public void deleteAllRequestsForBook(final Long bookId);

    // public List<TradeRequest> findAllRequestsForBook(final Long bookId);
}
