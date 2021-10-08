package com.sapient.programs;

import com.sapient.config.AppConfig4;
import com.sapient.dao.BookDao;
import com.sapient.entity.Book;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBookFromDb {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
        BookDao dao = ctx.getBean(BookDao.class);
        Book b1 = dao.getById(1);
        log.debug("b1 = {}", b1);
        ctx.close();
    }
}
