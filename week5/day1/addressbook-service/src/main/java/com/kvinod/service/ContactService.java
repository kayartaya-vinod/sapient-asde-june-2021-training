package com.kvinod.service;

import java.util.Optional;

import com.kvinod.dao.ContactDao;
import com.kvinod.entity.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {
    @Autowired
    private ContactDao dao;

    public Iterable<Contact> getContacts(String userId) throws ServiceException {
        try {
            return dao.findByUserId(userId);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void deleteContact(String id) throws ServiceException {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Contact addNewContact(Contact contact) throws ServiceException {
        try {
            return dao.save(contact);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Contact getContactById(String id) throws ServiceException {

        try {
            Optional<Contact> op = dao.findById(id);
            if (op.isPresent()) {
                return op.get();
            }
            throw new ServiceException("No data found for id " + id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Contact updateContact(Contact contact) throws ServiceException {
        try {
            log.info("contact is {}", contact);
            return dao.save(contact);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}
