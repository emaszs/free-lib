package com.psilonsoft.web.security;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.psilonsoft.model.entities.User;

/**
 * 
 * Currently executing user context. Stored in session.
 * 
 * the @Scope annotation makes this bean singleton per session - so we can safely store our user
 * information.
 * 
 * 
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = -728517862660424749L;

    /**
     * Id of user. Should map to {@link User#getId()}
     */
    private Long userId;

    /**
     * User email
     */
    private String email;

    /**
     * Role of user. Should map to {@link User#getRole()}
     */
    private User.Role role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(final User.Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return getUserId() != null;
    }
}
