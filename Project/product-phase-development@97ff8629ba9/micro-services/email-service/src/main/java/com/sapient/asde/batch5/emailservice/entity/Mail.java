package com.sapient.asde.batch5.emailservice.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Mail {
    private List<String> to = new ArrayList<>();
    // private String from;
    private String subject;
    private String message;
}
