package com.psilonsoft.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * 
 * Our main user entity - represents a single user in our system.
 * 
 * 
 * Note that we have mapped table user_ and not user - this is because user is a reserved word in
 * postgresql database.
 * 
 * 
 */
@Entity
@Table(name = "user_")
public class User {

    /**
     * Our id field that is auto generated from postgresql sequence. Sequence name has format of
     * {tableName}_{fieldName}_id;
     */
    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * Email of our user - this will be used to login users.
     */
    @NotNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * When was this user created?
     * 
     * The @Temporal(TemporalType.TIMESTAMP) indicates to hibernate that underlying data is stored
     * as timestamp in the database
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    /**
     * User role - represents what user can do in the system and his access rights.
     * 
     * The @Enumerated(EnumType.STRING) means that in the database actual strings will be stored.
     * (ex. LOGIN)
     * If we ommit this annotation, enums will be stored as numbers, so for value LOGIN we will
     * store value 0
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    /**
     * Indicates if user is active or not. For demo purposes we will have only active users.
     * 
     * This is jus to demonstrate that we can have boolean fields mapped.
     */
    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "debt")
    private int debt;

    /**
     * All actions that user has performed.
     * 
     * Note that FetchType is set to LAZY - this means that this list will only be fetched when we
     * will invoke getActions() method.
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Audit> actions;

    /**
     * Trades for which the user was responsible
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Trade> trades;

    /**
     * 
     * Possible user roles.
     * 
     * 
     */
    public enum Role {

        /**
         * Regular user - can take from inventory
         */
        REGULAR,
        /**
         * Admin - can send reminders, view admin pages, etc.
         */
        ADMIN
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(final List<Trade> trades) {
        this.trades = trades;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public List<Audit> getActions() {
        return actions;
    }

    public void setActions(final List<Audit> actions) {
        this.actions = actions;
    }

    // public List<Trade> getTrades() {
    // return trades;
    // }
    //
    // public void setTrades(final List<Trade> trades) {
    // this.trades = trades;
    // }

    public int getDebt() {
        return debt;
    }

    public void setDebt(final int debt) {
        this.debt = debt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (active != other.active) {
            return false;
        }
        if (createdOn == null) {
            if (other.createdOn != null) {
                return false;
            }
        } else if (!createdOn.equals(other.createdOn)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (role != other.role) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", createdOn=" + createdOn + ", role=" + role + ", active=" + active + ", actions=" + actions
                + "]";
    }

}
