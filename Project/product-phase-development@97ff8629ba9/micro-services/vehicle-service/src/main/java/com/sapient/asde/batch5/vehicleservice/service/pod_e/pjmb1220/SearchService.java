package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1220;


import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;


public interface SearchService {
    public List<Vehicle> findVehicleByText(String text,Integer page) throws ServiceException;

}
