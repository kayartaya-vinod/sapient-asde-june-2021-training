package com.sapient.entity;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private Double price;
}
