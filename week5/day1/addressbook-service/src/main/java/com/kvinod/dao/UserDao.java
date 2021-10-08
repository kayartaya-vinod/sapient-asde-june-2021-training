package com.kvinod.dao;

import com.kvinod.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {
    @Query("{email: ?0, password: ?1}")
    public User findByEmailAndPassword(String email, String password);
}
