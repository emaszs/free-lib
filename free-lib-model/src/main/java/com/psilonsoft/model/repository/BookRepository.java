package com.psilonsoft.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.psilonsoft.model.entities.Book;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BookRepository extends CrudRepository<Book, Long> {
    public Book findBookByName(final String name);

    @Query("delete from Book b where b.id = ?1")
    @Modifying
    public void removeBook(final Long bookId);


}
