package com.psilonsoft.model.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.psilonsoft.model.entities.Message;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.MessageRepository;
import com.psilonsoft.model.repository.UserRepository;

public class MessageRepositoryIT extends BaseModelTestIT {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void canCreateAndRetrieveMessage() {
        User user1 = Helpers.prepareFullyPopulatedUser();
        User user2 = Helpers.prepareFullyPopulatedUser();

        userRepository.save(user1);
        entityManager.flush();

        userRepository.save(user2);
        entityManager.flush();

        Message message = Helpers.prepareMessage(user2, user1);
        messageRepository.save(message);
        entityManager.flush();

        Assert.assertEquals("Expecting message contents to be the same", message.getContents(),
                messageRepository.findMessageById(message.getId()).getContents());
    }

}
