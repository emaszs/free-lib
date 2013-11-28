package com.psilonsoft.web.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.psilonsoft.services.BookService;
import com.psilonsoft.services.ItemService;
import com.psilonsoft.services.LoginService;
import com.psilonsoft.services.TradeRequestService;
import com.psilonsoft.services.TradeService;
import com.psilonsoft.services.UserService;

@Configuration
public class MockServicesConfig {

    @Bean
    public LoginService loginService() {
        return Mockito.mock(LoginService.class);
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public TradeService tradeService() {
        return Mockito.mock(TradeService.class);
    }

    @Bean
    public ItemService itemService() {
        return Mockito.mock(ItemService.class);
    }

    @Bean
    public BookService bookService() {
        return Mockito.mock(BookService.class);
    }
    
    @Bean
    public TradeRequestService tradeRequestService() {
        return Mockito.mock(TradeRequestService.class);
    }
}
