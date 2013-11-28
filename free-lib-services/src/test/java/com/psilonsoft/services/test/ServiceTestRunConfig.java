package com.psilonsoft.services.test;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.psilonsoft.model.repository.AuditRepository;
import com.psilonsoft.model.repository.BookRepository;
import com.psilonsoft.model.repository.ItemRepository;
import com.psilonsoft.model.repository.TradeRepository;
import com.psilonsoft.model.repository.UserRepository;

/**
 * 
 * Test configuration for our services project.
 * 
 * ComponentScan says to spring where to search for our services that are annotated with @Service
 * annotation.
 * 
 * 
 */
@Configuration
@ComponentScan("com.psilonsoft.services")
public class ServiceTestRunConfig {

    /**
     * Creates a {@link UserRepository} mock that we can actually manipulate.
     */
    @Bean
    public UserRepository userRepository() {
        return Mockito.mock(UserRepository.class);
    }

    /**
     * Creates a {@link AuditRepository} mock that we can actually manipulate.
     */
    @Bean
    public AuditRepository auditRepository() {
        return Mockito.mock(AuditRepository.class);
    }

    @Bean
    public ItemRepository ItemRepository() {
        return Mockito.mock(ItemRepository.class);
    }

    @Bean
    public TradeRepository tradeRepository() {
        return Mockito.mock(TradeRepository.class);
    }

    @Bean
    public BookRepository bookRepository() {
        return Mockito.mock(BookRepository.class);
    }
}
