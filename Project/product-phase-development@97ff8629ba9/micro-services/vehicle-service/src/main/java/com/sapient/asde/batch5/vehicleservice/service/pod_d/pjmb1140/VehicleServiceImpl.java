package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1140;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service("pjmb1140_VehicleServiceImpl")
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepository repository;

    /**
     * @author Deepthi S deepthi.s@publicissapient.com
     */
    @Override
    public Page<Vehicle> getByAdvancedSearch(String[] vehicleType, String[] brand, Double minPrice,Double maxPrice,
            String[] color, String[] fuelType,Integer airBagCount,Integer year,Integer page) throws ServiceException {

       return repository.findByAdvSearch(vehicleType, brand, minPrice, maxPrice,color,fuelType, airBagCount,year,page);
    }
    
}
