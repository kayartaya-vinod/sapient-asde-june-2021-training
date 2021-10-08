package com.sapient.asde.batch5.vehicleservice.repository;

import java.util.List;
import java.util.Optional;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleComparisonRepository extends MongoRepository<VehicleComparison, String> {
   
  /* @author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com */
  public List<VehicleComparison> getByUserId(String userId) throws ServiceException;

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   * @param id vehicleComparisonMatrix Id
   * @param userId id of loggedIn user
   * @return {@Optional<VehicleComparison> }
   */
  public Optional<VehicleComparison> findByIdAndUserId(String id, String userId);
}
