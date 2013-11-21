package com.psilonsoft.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.psilonsoft.model.entities.User;
import com.psilonsoft.services.TradeService;
import com.psilonsoft.services.UserService;
import com.psilonsoft.web.security.CurrentUser;

/**
 * 
 * Handles user editing.
 * 
 * @author gytis
 */
@Controller
public class UsersController {
    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private TradeService tradeService;

    /**
     * Special spring method, that registers custom conversion filter for String <-> Date
     * conversion.
     * 
     * @param binder
     */
    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss.SSS");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * Displays a list of existing users on the system
     */
    @RequestMapping("/users/list")
    public String list(final ModelMap modelMap) {
        modelMap.put("users", userService.getAll());
        return "users/list";
    }

    /**
     * Toggles active status for given user. Note that userId is extracted from path via @PathVariable
     * annotation.
     */
    @RequestMapping("/users/toggle-active/{userId}")
    public String toggleActive(final ModelMap modelMap, final @PathVariable Long userId) {
        User user = userService.get(userId);
        user.setActive(!user.isActive());
        userService.save(user);
        return "redirect:/users/list";
    }

    /**
     * shows user edit form.
     */
    @RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.GET)
    public String edit(
            final ModelMap modelMap,
            final @PathVariable Long userId) {
        modelMap.put("user", userService.get(userId));
        return "users/edit";
    }

    /**
     * Processes user edit form submit.
     */
    @RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.POST)
    public String update(
            final @PathVariable Long userId,
            final ModelMap modelMap,
            final  @ModelAttribute("user") @Valid User user,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        // if there were errors, return user edit form.
        if(bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.save(user);
        // add message that save was ok.
        redirectAttributes.addFlashAttribute("saveOk", "user-edit-saved");

        // and redirect back to user edit for, to prevent double submition.
        return "redirect:/users/edit/" + userId;
    }

    @RequestMapping("/users/userInfo")
    public String userInfo(final ModelMap modelMap) {
        User user = userService.get(currentUser.getUserId());
        modelMap.put("userDebt", user.getDebt());
        return "users/userInfo";
    }
}
