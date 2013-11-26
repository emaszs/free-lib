package com.psilonsoft.web.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psilonsoft.model.entities.User;
import com.psilonsoft.services.LoginService;
import com.psilonsoft.web.dto.LoginForm;
import com.psilonsoft.web.security.CurrentUser;

/**
 * 
 * Controller that handles login/logout requests.
 * 
 * 
 */
@Controller
public class LoginLogoutController {

    private static final Logger log = LoggerFactory.getLogger(LoginLogoutController.class);

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private LoginService loginService;

    /**
     * Represents user login form.
     * 
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(final ModelMap model) {
        // add login form attribute
        model.put("loginForm", new LoginForm());
        return "login";
    }

    /**
     * Processes login form.
     * 
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(
            final ModelMap model,
            final @Valid @ModelAttribute("loginForm") LoginForm loginForm,
            final BindingResult bindingResult) {

        User user = null; 
        // lets check for errors
        if (!bindingResult.hasErrors()) {

            // no errors, lets try to login user.
            user = loginService.login(loginForm.getEmail());
            if (user == null) {
                // something has failed, reject it with a global errror.
                bindingResult.reject("login-generic-fail");
            } 
        }
        
        // at this point, we should have a user. If no user - return same login form.
        if (user == null) {
            return "login";
        }

        log.debug("Logging in user with id {} and role {}", user.getId());

        // all ok - add user details to the session, and redirect to front page.
        currentUser.setUserId(user.getId());
        currentUser.setEmail(user.getEmail());
        return "redirect:/";
    }
}
