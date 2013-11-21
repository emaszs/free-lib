package com.psilonsoft.web.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * Represents our login form model. Every spring form has to have a model associated with it.
 * 
 * It might be our entity, or we might have a custom class
 * 
 * @author gytis
 */
public class LoginForm {

    @Email(message = "{login-invalid-email}")
    @NotEmpty(message = "{login-no-email}")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
