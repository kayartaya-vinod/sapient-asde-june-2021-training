package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1767;

import java.util.Map;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import com.sapient.asde.batch5.vehicleservice.entity.Select;

/**
 * @Author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
 */
public interface BrandAndModelService {

    public List<Select> getBrandAndModel(String brand, String model,Integer page) throws ServiceException;

    public List<Select> getBrand() throws ServiceException;
    
}
