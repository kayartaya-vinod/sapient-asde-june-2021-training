package com.sapient.asde.batch5.customerservice.repository;

import java.util.List;

import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.entity.Vehicle;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

      public Customer findByUserId(String userId);

      public List<Vehicle> findByWishlist();

}
