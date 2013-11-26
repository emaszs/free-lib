package com.psilonsoft.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Trade;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface TradeRepository extends CrudRepository<Trade, Long> {
    // public List<Trade> findByUser(User user);

    @Override
    public Iterable<Trade> findAll();
}
