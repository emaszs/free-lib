package com.psilonsoft.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Item;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ItemRepository extends CrudRepository<Item, Long> {
    // @Modifying
    // @Query("UPDATE Item i SET i.stock = i.stock - 1 WHERE i = ?1")
    // public void decrementItemStock(Item item);
}