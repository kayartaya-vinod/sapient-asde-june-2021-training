package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1208;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
public interface ComparisonMatrixMetadataService {

    /* @author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com */
    public List<VehicleComparison> getMetadataByUserId(String userId) throws ServiceException; 
}
