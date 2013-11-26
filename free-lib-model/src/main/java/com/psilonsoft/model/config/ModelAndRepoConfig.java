package com.psilonsoft.model.config;

import java.sql.DriverManager;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@EnableAspectJAutoProxy
@EnableSpringConfigured
@Configuration
@EnableJpaRepositories(basePackages = "com.psilonsoft.model.repository")
@EnableTransactionManagement
public class ModelAndRepoConfig {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public DataSource dataSource() throws Exception {

        Resource resource = new ClassPathResource("db.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);

        return new LazyConnectionDataSourceProxy(new SimpleDriverDataSource(
                DriverManager.getDriver(props.getProperty("url")),
                props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password")));
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaDialect createJpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean
    public JpaVendorAdapter createJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        return vendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws Exception {

        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(createJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.psilonsoft.model.entities");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaDialect(createJpaDialect());

        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "create");

        props.put("jadira.usertype.autoRegisterUserTypes", true);
        props.put("jadira.usertype.databaseZone", "jvm");
        props.put("jadira.usertype.javaZone", "jvm");

        factoryBean.setJpaProperties(props);
        factoryBean.afterPropertiesSet();

        EntityManagerFactory factory = factoryBean.getObject();

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

}
