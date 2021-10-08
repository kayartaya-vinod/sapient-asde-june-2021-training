package com.sapient.dao;

import com.sapient.entity.Contact;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends PagingAndSortingRepository<Contact, String> {

}
