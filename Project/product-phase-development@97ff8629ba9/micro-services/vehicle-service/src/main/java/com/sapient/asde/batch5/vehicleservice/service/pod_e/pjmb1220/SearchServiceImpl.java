package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1220;


import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.SearchRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
   
    @Autowired
    SearchRepository repo;



    @Cacheable(value ="VehicleSearch",unless="#result == null")
    @Override
    public List<Vehicle> findVehicleByText(String text,Integer page) throws ServiceException {
       return repo.searchByText(text,page*12);
    }

}
