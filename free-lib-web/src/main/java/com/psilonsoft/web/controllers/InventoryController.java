package com.psilonsoft.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psilonsoft.model.entities.Item;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.services.ItemService;
import com.psilonsoft.services.TradeService;
import com.psilonsoft.services.UserService;
import com.psilonsoft.services.exceptions.NoItemsLeftException;
import com.psilonsoft.web.security.CurrentUser;

/**
 * 
 * Handles display of whole inventory with an option to "take" certain items.
 * 
 * @author Emilis
 */

@Controller
public class InventoryController {
    private static final Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    CurrentUser currentUser;

    @Autowired
    TradeService tradeService;

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @RequestMapping("/items/inventory")
    public String inventory(final ModelMap modelMap) {
        modelMap.put("items", itemService.getAll());
        return "items/inventory";
    }

    @RequestMapping("/items/take-item/{itemId}")
    public String takeItem(final ModelMap modelMap, final @PathVariable Long itemId) {
        Item item = itemService.getById(itemId);
        User user = userService.get(currentUser.getUserId());

        log.debug("Taking item with id " + itemId + " and user email " + currentUser.getEmail());

        try {
            tradeService.logTakenItem(item, user);
        } catch (NoItemsLeftException e) {
            log.debug(e.getMessage());
        }
        return "redirect:/items/inventory";
    }
}
