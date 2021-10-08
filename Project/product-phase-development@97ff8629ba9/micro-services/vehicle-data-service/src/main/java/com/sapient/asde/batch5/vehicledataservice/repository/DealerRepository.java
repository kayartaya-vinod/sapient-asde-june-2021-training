package com.sapient.asde.batch5.vehicledataservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sapient.asde.batch5.vehicledataservice.entity.Dealer;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends MongoRepository<Dealer, String>{
    public Dealer findByUserId(String userId);
}
