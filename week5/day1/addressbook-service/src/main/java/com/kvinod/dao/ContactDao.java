package com.kvinod.dao;

import com.kvinod.entity.Contact;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactDao extends PagingAndSortingRepository<Contact, String> {
    public Iterable<Contact> findByUserId(String userId);
}
