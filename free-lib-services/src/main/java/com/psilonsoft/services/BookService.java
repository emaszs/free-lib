package com.psilonsoft.services;

import com.psilonsoft.model.entities.Book;

/**
 * This class is meant to handle operations involving books - addition and removal, as well as
 * displaying available books to the user.
 * 
 * 
 */
public interface BookService {
    public void addBook(Long userId,
            String name,
            String categories,
            String description,
            String img);

    public void save(Book book);
}
