package com.psilonsoft.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Message;
import com.psilonsoft.model.entities.User;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query("select a from Audit a where a.user.id = ?1")
    public List<Message> findMessagesBelongingToUser(User user);

}
