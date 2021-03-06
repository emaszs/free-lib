package com.psilonsoft.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.psilonsoft.model.entities.Book;
import com.psilonsoft.model.entities.User;
import com.psilonsoft.services.BookService;
import com.psilonsoft.services.TradeRequestService;
import com.psilonsoft.services.UserService;
import com.psilonsoft.web.security.CurrentUser;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private CurrentUser currentUser;
    
    @Autowired
    private TradeRequestService tradeRequestService;

    @RequestMapping(value = "/book/list")
    public String listBooks(final ModelMap modelMap) {
        modelMap.put("books", bookService.getAll());
        return "book/list";
    }

    @RequestMapping(value = "/book/add")
    public String addBookForm(@ModelAttribute("book") final Book book) {
        return "/book/add";
    }

    @RequestMapping(value = "/book/add", method = { RequestMethod.POST })
    public String addBook(@ModelAttribute("book") final Book book, final BindingResult result) {

        if (book.getName() == null || book.getName().isEmpty()) {
            result.rejectValue("name", "empty-name", "Book name is empty");
        }

        if (book.getDescription() == null || book.getDescription().isEmpty()) {
            result.rejectValue("description", "empty-description", "Book description is empty");
        }

        if(result.hasErrors()) {
            return "book/add";
        }

        book.setUser(userService.get(currentUser.getUserId()));

        bookService.save(book);

        return "redirect:/book/add";
    }

    @RequestMapping(value = "/book/send-request/{bookId}", method = { RequestMethod.POST })
    public String sendTradeRequest(final ModelMap modelMap, @PathVariable final Long bookId) {
        Book book = bookService.findOne(bookId);
        User userFrom = userService.get(currentUser.getUserId());
        User userTo = book.getUser();

        if (book != null && userFrom != null && userTo != null) {
            tradeRequestService.createNewRequest(userFrom, userTo, book);
            return "redirect:/book/list";
        }

        return "redirect:/book/list";
    }
}
