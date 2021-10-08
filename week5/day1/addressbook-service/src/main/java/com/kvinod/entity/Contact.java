package com.kvinod.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "contacts")
@Data
public class Contact {
    @Id
    private String id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String userId;
}
