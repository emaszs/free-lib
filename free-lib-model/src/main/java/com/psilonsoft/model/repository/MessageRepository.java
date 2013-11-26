package com.psilonsoft.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Message;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MessageRepository extends CrudRepository<Message, Long> {

    public Message findMessageById(final Long id);
}
