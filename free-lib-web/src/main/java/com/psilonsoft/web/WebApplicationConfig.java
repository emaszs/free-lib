package com.psilonsoft.web;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.psilonsoft.web.security.UserRequiredInterceptor;

/**
 * 
 * Spring MVC and our web application central configuration.
 * 
 * 
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.psilonsoft.web.controllers", "com.psilonsoft.web.security" })
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public static final Locale DEFAULT_LOCALE = new Locale("lt", "LT");

    @Autowired
    private UserRequiredInterceptor requiredInterceptor;

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MappingJacksonHttpMessageConverter jacksonConverter() {
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        return converter;
    }

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
        configurer.favorParameter(false);
        configurer.ignoreAcceptHeader(false);
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("html", new MediaType("text", "html", UTF8));
        mediaTypes.put("json", new MediaType("application", "json", UTF8));
        mediaTypes.put("*", new MediaType("*", "*"));
        configurer.mediaTypes(mediaTypes);
        super.configureContentNegotiation(configurer);
    }


    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return jacksonConverter().getObjectMapper();
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("lt", "LT"));
        return resolver;
    }

    @Override
    public Validator getValidator() {

        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        @SuppressWarnings("deprecation")
        ResourceBundleMessageInterpolator interpolator =
                new ResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource()), false);
        factoryBean.setMessageInterpolator(interpolator);

        return factoryBean;
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", UTF8)));
        converters.add(stringConverter);
        converters.add(jacksonConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:language");
        messageSource.setDefaultEncoding(UTF8.name());
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(30);

        return messageSource;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        // ensures that all paths must required logged in user, except the login path.
        registry.addInterceptor(requiredInterceptor).addPathPatterns("/**").excludePathPatterns("/login**");
    }


}
