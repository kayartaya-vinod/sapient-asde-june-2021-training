package com.sapient.asde.batch5.authservice.repository;
import com.sapient.asde.batch5.authservice.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {
    @Query("{email: ?0, password: ?1}")
    public User findByEmailAndPassword(String email, String password);
    public User findByEmail(String email);
}
