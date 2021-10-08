package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1349;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("pjmb1349_getVehicleListByDealerIdServiceImp")
public class GetVehicleListByDealerIdServiceImp implements GetVehicleListByDealerIdService {

    @Autowired
    private VehicleRepository repo;

    @Override
    public Page<Vehicle> getVehicleListByDealerId(String dealerId, Integer page) throws ServiceException {
        try {
            Pageable pageable = PageRequest.of(page - 1, 12);
            Page<Vehicle> result = repo.findByDealerId(dealerId, pageable);
            if(result.hasContent()){
                return result;
            }else{
                throw new ServiceException("No vehicles found", HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getStatus());
        }
    }
    
}
