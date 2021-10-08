package com.sapient.dao;

import com.sapient.entity.Book;

public class JpaBookDao implements BookDao {

    @Override
    public Book getById(Integer id) {
        // return a dummy object
        Book book = new Book();
        book.setId(id);
        book.setTitle("Java unleashed");
        book.setPublisher("McHill");
        book.setPrice(999.0);
        return book;
    }

}
