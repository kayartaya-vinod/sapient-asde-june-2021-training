package com.sapient.service;

import com.sapient.dao.ContactDao;
import com.sapient.entity.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactDao dao;

    public Iterable<Contact> getContacts(int pageNum, int pageSize) throws ServiceException {
        try {
            return dao.findAll(PageRequest.of(pageNum - 1, pageSize));
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

}
