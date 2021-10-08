package com.sapient.asde.batch5.vehicleservice.repository;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    @Query("{'vehicleId':?0,'userId':?1}")
    public Feedback getByVehicleIdAndUserId(String vehicleId, String userId);

    @Query("{'vehicleId':?0,'userId':?1}")
    public Feedback findByVehicleIdUserId(String vehicleId, String userId);

    @Query("{'vehicleId':?0}")
    public List<Feedback> getByVehicleId(String vehicleId);

    public List<Feedback> findAll();

    public List<Feedback> findByVehicleId(String vid);

    @Query("{ 'vehicleId': {'$in': ?0} }")
    public Page<Feedback> findAllByVehicleIds(List<String> vids, Pageable page);

}
