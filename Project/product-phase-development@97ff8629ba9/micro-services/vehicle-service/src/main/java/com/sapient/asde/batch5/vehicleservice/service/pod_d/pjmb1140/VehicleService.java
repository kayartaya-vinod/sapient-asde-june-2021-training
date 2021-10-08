package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1140;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.data.domain.Page;

public interface VehicleService {

    /**
     * @author Deepthi S deepthi.s@publicissapient.com
     * @param page
     */
    public Page<Vehicle> getByAdvancedSearch(String[] vehicleType, String[] brand, Double minPrice,Double maxPrice,
            String[]  color, String[] fuelType, Integer airBagCount, Integer year, Integer page) throws ServiceException;

}
