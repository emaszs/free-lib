package com.psilonsoft.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Book entity - represents a book that is available for trading.
 * 
 * @author Emilis
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "categories")
    private String categories;

    /**
     * A user that is offering the book
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}
