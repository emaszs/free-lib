package com.psilonsoft.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.services.BookService;
import com.psilonsoft.services.TradeRequestService;

@Controller
public class TradeRequestController {
    @Autowired
    TradeRequestService tradeRequestService;
    
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/trade-request/list")
    public String listRequests(final ModelMap modelMap) {
        modelMap.put("tradeRequests", tradeRequestService.getAll());
        return "trade-request/list";
    }
    
    // TODO check modelmap necessity
    @RequestMapping(value = "/trade-request/accept/{requestId}", method = {RequestMethod.POST})
    public String acceptRequest(final ModelMap modelMap, @PathVariable final Long requestId) {
        Long bookId = tradeRequestService.findOne(requestId).getBook().getId();
        Book book = bookService.findOne(bookId);
        bookService.removeBook(book);
        // TODO complete accept method
        return null;
    }

    // TODO check modelmap necessity
    @RequestMapping(value = "/trade-request/decline/{requestId}", method = { RequestMethod.POST })
    public String declineRequest(final ModelMap modelMap, @PathVariable final Long requestId) {
        tradeRequestService.deleteRequest(requestId);
        // TODO complete decline method
        return "trade-request/list";
    }
}
