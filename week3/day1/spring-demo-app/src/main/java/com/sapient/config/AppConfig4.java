package com.sapient.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// This class defines all the beans and their dependencies
@Configuration
@ComponentScan(basePackages = { "com.sapient.dao" })
public class AppConfig4 {

    @Bean
    public BasicDataSource ds() {
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

}
