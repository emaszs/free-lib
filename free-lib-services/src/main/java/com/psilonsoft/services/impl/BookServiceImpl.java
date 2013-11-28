package com.psilonsoft.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.repository.BookRepository;
import com.psilonsoft.model.repository.UserRepository;
import com.psilonsoft.services.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void addBook(final Long userId,
            final String name,
            final String categories,
            final String description,
            final String img) {
        Book book = new Book();
        book.setName(name);
        book.setCategories(categories);
        book.setDescription(description);
        book.setImg(img);
        book.setUser(userRepository.findOne(userId));

        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void save(final Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }
}
