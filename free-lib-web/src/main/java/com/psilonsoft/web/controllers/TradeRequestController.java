package com.psilonsoft.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.services.BookService;
import com.psilonsoft.services.TradeRequestService;
import com.psilonsoft.web.security.CurrentUser;

@Controller
public class TradeRequestController {
    private static final Logger log = LoggerFactory.getLogger(TradeRequestController.class);

    @Autowired
    TradeRequestService tradeRequestService;

    @Autowired
    CurrentUser currentUser;

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/trade-request/list", method = { RequestMethod.GET })
    public String listRequests(final ModelMap modelMap) {
        modelMap.put("tradeRequests",
                tradeRequestService.getRequestsBelongingToUser(currentUser.getUserId()));
        return "trade-request/list";
    }

    // TODO check modelmap necessity
    @RequestMapping(value = "/trade-request/accept/{requestId}", method = { RequestMethod.POST })
    public String acceptRequest(final ModelMap modelMap, @PathVariable final Long requestId) {
        Long bookId = tradeRequestService.findOne(requestId).getBook().getId();
        Book book = bookService.findOne(bookId);

        tradeRequestService.deleteRequestsAssociatedWithBook(bookId);

        bookService.removeBook(bookId);

        // TODO complete accept method
        return "redirect:/trade-request/list";
    }

    // TODO check modelmap necessity
    @RequestMapping(value = "/trade-request/decline/{requestId}", method = { RequestMethod.POST })
    public String declineRequest(final ModelMap modelMap, @PathVariable final Long requestId) {
        tradeRequestService.deleteRequest(requestId);
        // TODO complete decline method
        return "redirect:/trade-request/list";
    }
}
