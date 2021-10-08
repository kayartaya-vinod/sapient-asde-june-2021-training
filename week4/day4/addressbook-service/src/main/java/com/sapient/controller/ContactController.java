package com.sapient.controller;

import com.sapient.entity.Contact;
import com.sapient.service.ContactService;
import com.sapient.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin
public class ContactController {

    @Autowired
    ContactService service;

    @GetMapping
    public ResponseEntity<Object> getAllContacts(
            @RequestParam(name = "_page", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "_limit", required = false, defaultValue = "10") Integer pageSize)
            throws ServiceException {
        return ResponseEntity.ok(service.getContacts(pageNum, pageSize));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContat(@PathVariable String id) throws ServiceException {
        service.deleteContact(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) throws ServiceException {
        return ResponseEntity.ok(service.addNewContact(contact));
    }

}
