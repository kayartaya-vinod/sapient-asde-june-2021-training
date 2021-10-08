package com.sapient.asde.batch5.emailservice.controller;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.emailservice.entity.Mail;
import com.sapient.asde.batch5.emailservice.service.ServiceException;
import com.sapient.asde.batch5.emailservice.service.SimpleMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/mail")
public class SimpleMailController {
    @Autowired
    SimpleMailService service;

    @Autowired
    ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<Object> sendMail(@RequestBody Map<String, Object> payload) {

        Map<String, Object> map = new HashMap<>();
        try {
            Mail mail = mapper.convertValue(payload.get("mail"), Mail.class);
            service.sendMailMessage(mail);
            map.put("success", true);
            return ResponseEntity.ok(map);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
