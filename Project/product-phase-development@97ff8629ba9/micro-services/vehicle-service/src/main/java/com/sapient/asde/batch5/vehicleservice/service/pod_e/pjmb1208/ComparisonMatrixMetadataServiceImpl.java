package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1208;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleComparisonRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("pjmb1208_comparisonMatrixMetadataServiceImpl")
public class ComparisonMatrixMetadataServiceImpl implements ComparisonMatrixMetadataService {

    @Autowired
    private VehicleComparisonRepository repo;

    /* @author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com */
    @Override
    public List<VehicleComparison> getMetadataByUserId(String userId) throws ServiceException {

        try {
            List<VehicleComparison> result = repo.getByUserId(userId);
            if (!result.isEmpty()) {
                return result;
            } else {
                throw new ServiceException("No saved comparisons found", HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getStatus());
        }
    }
}
