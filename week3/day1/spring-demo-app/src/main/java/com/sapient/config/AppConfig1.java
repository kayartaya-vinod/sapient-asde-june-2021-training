package com.sapient.config;

import com.sapient.dao.BookDao;
import com.sapient.dao.JdbcBookDao;
import com.sapient.dao.JpaBookDao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import lombok.extern.slf4j.Slf4j;

// This class defines all the beans and their dependencies
@Configuration
@Slf4j
public class AppConfig1 {

    public AppConfig1() {
        log.debug("AppConfig1() constructor called");
    }

    // Spring invokes all the functions in this class, decorated with @Bean
    // annoation, collects the return value, and keeps in the container with the
    // name of the class as the name of the bean
    @Bean
    @Lazy
    public BookDao jdbcBookDao() {
        log.debug("AppConfig1.jdbcBookDao() called...");
        return new JdbcBookDao();
    }

    @Scope("prototype")
    @Bean
    public BookDao jpaBookDao() {
        log.debug("AppConfig1.jpaBookDao() called...");
        return new JpaBookDao();
    }
}
