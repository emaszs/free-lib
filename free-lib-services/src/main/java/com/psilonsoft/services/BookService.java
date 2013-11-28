package com.psilonsoft.services;

import java.util.List;

import com.psilonsoft.model.entities.Book;

/**
 * This service is meant to handle operations involving books - addition and removal, as well as
 * displaying available books to the user.
 * 
 */
public interface BookService {
    /**
     * Constructs and saves a book from parameters.
     * 
     * @param userId
     * @param name
     * @param categories
     * @param description
     * @param img
     */
    public void addBook(Long userId,
            String name,
            String categories,
            String description,
            String img);


    /**
     * Saves a book to a repository
     * 
     * @param book
     */
    public void save(Book book);

    /**
     * Gets a list of all books in the database
     * 
     * @return List<Book>
     */
    public List<Book> getAll();

    /**
     * Finds one book by it's id
     * 
     * @return Book
     */
    public Book findOne(Long bookId);
}
