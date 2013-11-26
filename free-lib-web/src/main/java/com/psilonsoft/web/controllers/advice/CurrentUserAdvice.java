package com.psilonsoft.web.controllers.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.psilonsoft.web.security.CurrentUser;

/**
 * 
 * Advice is a special type of controller, that is executed for every request.
 * This allows us to expose common functionality to jsp files, without repeating this code in
 * controllers.
 * 
 * 
 */
@ControllerAdvice
public class CurrentUserAdvice {

    @Autowired
    private CurrentUser currentUser;

    /**
     * this model attribute will be available in all jsp files. you can access it via ${currentUser}
     * 
     * @return
     */
    @ModelAttribute("currentUser")
    public String getCurrentUser() {
        return currentUser.getEmail();
    }

}
