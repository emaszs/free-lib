package com.psilonsoft.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Book;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BookRepository extends CrudRepository<Book, Long> {
    public Book findBookByName(final String name);
}