package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1154;

import java.util.Optional;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleComparisonRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@Slf4j
@Service("pjmb1154_vehicleComparisonServiceImpl")
public class VehicleComaprisonServiceImpl implements VehicleComparisonService {

  @Autowired
  VehicleComparisonRepository repo;

  @Cacheable(value = "Comparison")
  @Override
  public VehicleComparison getComparisonMatrix(String id, String userId) throws ServiceException {
      Optional<VehicleComparison> resultValue = repo.findByIdAndUserId(id, userId);
      if(resultValue.isPresent()){
        log.info("Found Matching id, Returning result");
        return resultValue.get();
      }
      else{
        log.info("No Matching Id Found");
        throw new ServiceException("No matching Id found", HttpStatus.NOT_FOUND);
      }
  }

}
