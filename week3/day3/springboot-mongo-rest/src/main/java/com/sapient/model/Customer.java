package com.sapient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "customers")
@Data
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String country;
}
