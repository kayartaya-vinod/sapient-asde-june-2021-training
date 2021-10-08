package com.sapient.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "contacts")
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String city;
    private String state;
}
