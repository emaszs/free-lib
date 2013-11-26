package com.psilonsoft.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Audit log entity - represents a single action that user has executed. Used for logging and audit
 * purposes.
 * 
 * 
 */
@Entity
@Table(name = "audit")
public class Audit {

    /**
     * Our id field that is auto generated from postgresql sequence. Sequence name has format of
     * {tableName}_{fieldName}_id;
     */
    @Id
    @SequenceGenerator(name = "audit_id_seq", sequenceName = "audit_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_id_seq")
    @Column(name = "id", updatable = false)
    private Long id;
    
    /**
     * Which user has executed the action? This links to {@link User} entity and user_ table.
     * 
     * This means that in table audit we have a field user_id that is mapped to user_ tables id
     * field.
     */
    @ManyToOne
    // (optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    /**
     * When did this action occur?
     * 
     * The @Temporal(TemporalType.TIMESTAMP) indicates to hibernate that underlying data is stored
     * as timestamp in the database
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;
    
    /**
     * Action we are logging.
     * 
     * The @Enumerated(EnumType.STRING) means that in the database actual strings will be stored.
     * (ex. LOGIN)
     * If we ommit this annotation, enums will be stored as numbers, so for value LOGIN we will
     * store value 0
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action;
    
    /**
     * 
     * Possible audit actions in our system.
     * 
     * 
     */
    public enum Action {
        /**
         * User has logged in into the system.
         */
        LOGIN,
        /**
         * User has taken goods out of our inventory.
         */
        TAKE_GOODS
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(final Action action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Audit other = (Audit) obj;
        if (action != other.action) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!user.equals(other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Audit [id=" + id + ", user=" + user + ", date=" + date + ", action=" + action + "]";
    }

}
