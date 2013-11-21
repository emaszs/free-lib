package com.psilonsoft.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.User;
import com.psilonsoft.model.repository.UserRepository;
import com.psilonsoft.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User get(final Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    @Transactional
    public User save(final User user) {
        return userRepository.save(user);
    }

}
