package com.sapient.config;

import javax.sql.DataSource;

import com.sapient.dao.BookDao;
import com.sapient.dao.JdbcBookDao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

// This class defines all the beans and their dependencies
@Configuration
public class AppConfig2 {

    @Bean
    public BasicDataSource basicDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl("jdbc:mysql://localhost/springdb");
        bds.setUsername("root");
        bds.setPassword("Welcome#123");
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setInitialSize(10);
        bds.setMaxTotal(150);
        bds.setMaxIdle(2);
        bds.setMaxWaitMillis(2000);
        return bds;
    }

    @Bean
    @Lazy
    public BookDao jdbcBookDao(DataSource ds) { // dependency injection
        JdbcBookDao dao = new JdbcBookDao();
        dao.setDs(ds); // manual wiring
        return dao;
    }

}
