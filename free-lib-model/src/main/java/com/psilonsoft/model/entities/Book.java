package com.psilonsoft.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "categories")
    private String categories;

    @Column(name = "description")
    private String description;

    @Column(name = "img")
    private String img;

    /**
     * A user that is offering the book
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    private List<TradeRequest> tradeRequests;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(final String categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(final String img) {
        this.img = img;
    }

    public List<TradeRequest> getTradeRequests() {
        return tradeRequests;
    }

    public void setTradeRequests(final List<TradeRequest> tradeRequests) {
        this.tradeRequests = tradeRequests;
    }

}
