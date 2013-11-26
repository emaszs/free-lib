package com.psilonsoft.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.psilonsoft.model.config.ModelAndRepoConfig;
import com.psilonsoft.services.ServicesConfig;

/**
 * 
 * Base servlet initializer needed to bootstrap our spring.
 * 
 * 
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Servlet startup.
     */
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {

        FilterRegistration.Dynamic characterEncodingFilter =
                servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true,
                "/*");
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");

        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // configuration classes from model and services projects should be included here.
        return new Class[] { ModelAndRepoConfig.class, ServicesConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebApplicationConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // under what path does our application
        return new String[] { "/" };
    }

}
