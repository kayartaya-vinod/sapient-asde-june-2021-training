package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1155;

import java.util.Optional;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleComparisonRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@Slf4j
@Service("pjmb1155_vehiclecomparisonserviceimpl")
public class VehicleComaprisonServiceImpl implements VehicleComparisonService {

  @Autowired
  VehicleComparisonRepository comparisonRepo;

  @CacheEvict(value = "Comparison")
  @Override
  public void deleteComparisonMatrixById(String id, String userId) throws ServiceException {
    if (id == null || id.trim().isEmpty()) {
      log.debug("deleteComparisonMatrixById - Vehicle Comparison Matrix id not supplied.");
      throw new ServiceException("Vehicle comparison id cannot be empty.", HttpStatus.BAD_REQUEST);
    }
    if (userId == null || userId.trim().isEmpty()) {
      log.debug("deleteComparisonMatrixById user id not supplied.");
      throw new ServiceException("User id cannot be empty.", HttpStatus.BAD_REQUEST);
    }

    try {
      Optional<VehicleComparison> optionalComp = comparisonRepo.findById(id);

      if (!optionalComp.isPresent()) {
        log.debug("deleteComparisonMatrixById - Vehicle Comparison Matrix id not found.");
        throw new ServiceException("Vehicle comparison matrix not found.", HttpStatus.NOT_FOUND);
      }

      VehicleComparison comp = optionalComp.get();

      if (!comp.getUserId().equals(userId)) {
        log.debug("deleteComparisonMatrixById - user {} Unauthorized to delete Vehicle Comparison Matrix {}.", userId,
            id);
        throw new ServiceException("Unauthorized Access.", HttpStatus.UNAUTHORIZED);
      }

      comparisonRepo.deleteById(id);
      log.info("deleteComparisonMatrixById - Delete Vehicle Comparison Matrix {} for user {}.", id, userId);

    } catch (ServiceException e) {
      throw new ServiceException(e.getMessage(), e.getStatus());

    }
  }

}
