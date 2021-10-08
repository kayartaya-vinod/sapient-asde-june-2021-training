package com.kvinod.controller;

import com.kvinod.entity.Contact;
import com.kvinod.service.ContactService;
import com.kvinod.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/contacts")
@CrossOrigin
public class ContactController {

    @Autowired
    ContactService service;

    // this function is called by DispatcherServlet (of Spring) and we expect the
    // userId (which is extracted from the JWT), to be injected.
    @GetMapping
    public ResponseEntity<Object> getAllContacts(@RequestAttribute(name = "userId", required = false) String userId)
            throws ServiceException {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized. please login");
        }
        return ResponseEntity.ok(service.getContacts(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContactById(@PathVariable String id,
            @RequestAttribute(name = "userId", required = false) String userId) throws ServiceException {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized; please login");
        }
        return ResponseEntity.ok(service.getContactById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteContat(@PathVariable String id,
            @RequestAttribute(name = "userId", required = false) String userId) throws ServiceException {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized; please login");
        }
        service.deleteContact(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> addContact(@RequestBody Contact contact,
            @RequestAttribute(name = "userId", required = false) String userId) throws ServiceException {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized; please login");
        }
        contact.setUserId(userId);
        return ResponseEntity.ok(service.addNewContact(contact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContact(@PathVariable String id, @RequestBody Contact contact,
            @RequestAttribute(name = "userId", required = false) String userId) throws ServiceException {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized; please login");
        }
        contact.setUserId(userId);
        contact.setId(id);
        Contact c = service.updateContact(contact);
        log.info("c is {}", c);
        return ResponseEntity.ok(c);
    }

}
