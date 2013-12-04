package com.psilonsoft.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psilonsoft.services.BookService;
import com.psilonsoft.services.TradeRequestService;
import com.psilonsoft.web.security.CurrentUser;

@Controller
public class TradeRequestController {

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

    @RequestMapping(value = "/trade-request/accept/{requestId}", method = { RequestMethod.POST })
    public String acceptRequest(@PathVariable final Long requestId) {
        Long bookId = tradeRequestService.findOne(requestId).getBook().getId();

        tradeRequestService.deleteRequestsAssociatedWithBook(bookId);

        bookService.removeBook(bookId);

        return "redirect:/trade-request/list";
    }

    @RequestMapping(value = "/trade-request/decline/{requestId}", method = { RequestMethod.POST })
    public String declineRequest(@PathVariable final Long requestId) {
        tradeRequestService.deleteRequest(requestId);
        return "redirect:/trade-request/list";
    }
}
