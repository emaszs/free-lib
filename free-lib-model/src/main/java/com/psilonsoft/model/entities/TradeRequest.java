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
 * TradeRequest entity represents a notification to users whenever someone offers a trade to them.
 * 
 * Usage scenario example:
 * 
 * Let's say Book1 is being offered by User1 as available for trading.
 * 
 * Then, User2 sees the Book1 and wants to initiate a trade with User1 for that book.
 * 
 * A tradeRequest is then sent to User1 with trade details - what book is User2 offering, etc.
 * 
 * User1 can then respond to the request by either accepting or declining. Declining simply deletes
 * the request, while accepting also removes Book1 from the system.
 * 
 */
@Entity
@Table(name = "tradeRequest")
public class TradeRequest {

    @Id
    @SequenceGenerator(name = "tradeRequest_id_seq", sequenceName = "tradeRequest_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tradeRequest_id_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_from_id", nullable = false)
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "user_to_id", nullable = false)
    private User userTo;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "contents")
    private String contents;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(final User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(final User userTo) {
        this.userTo = userTo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(final String contents) {
        this.contents = contents;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(final Book book) {
        this.book = book;
    }

}
