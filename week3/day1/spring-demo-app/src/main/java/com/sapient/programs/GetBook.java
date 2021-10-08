package com.sapient.programs;

import com.sapient.config.AppConfig1;
import com.sapient.dao.BookDao;
import com.sapient.entity.Book;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBook {

    public static void main(String[] args) {

        // uses / depends on BookDao
        BookDao dao;

        // tight coupling
        // dao = new JpaBookDao()

        // this is a variable representing the spring container
        AnnotationConfigApplicationContext ctx;

        // this is an object representing the spring container
        log.debug("Creating a spring container....");
        ctx = new AnnotationConfigApplicationContext(AppConfig1.class);
        log.debug("Creating spring container finished!");

        // get a bean from spring container
        dao = ctx.getBean("jpaBookDao", BookDao.class);

        BookDao dao1 = ctx.getBean("jpaBookDao", BookDao.class);

        log.debug("dao1==dao is {}", dao1 == dao);

        Book b = dao.getById(12);

        log.debug("b = {}", b);

        ctx.getBean("jdbcBookDao", BookDao.class);
        ctx.getBean("jdbcBookDao", BookDao.class);
        ctx.getBean("jdbcBookDao", BookDao.class);
        ctx.getBean("jdbcBookDao", BookDao.class);
        ctx.close();
    }
}
