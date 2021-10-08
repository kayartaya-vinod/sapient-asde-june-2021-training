package com.sapient.asde.batch5.customerservice.repository;


import com.sapient.asde.batch5.customerservice.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
