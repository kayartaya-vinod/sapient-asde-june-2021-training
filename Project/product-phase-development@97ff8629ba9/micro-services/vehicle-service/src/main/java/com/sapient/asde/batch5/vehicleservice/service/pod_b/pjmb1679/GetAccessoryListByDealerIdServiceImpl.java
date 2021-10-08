package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1679;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.repository.AccessoryRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
/**
 * @author Manvendra Singh
 */
@Service("pjmb1679_getAccessoryListByDealerIdServiceImpl")
public class GetAccessoryListByDealerIdServiceImpl implements GetAccesssoryListByDealerIdService {

    @Autowired
    private AccessoryRepository repository;

    @Override
    public Page<VehicleAccessory> getAccessoryListByDealerId(String dealerId, Integer page) throws ServiceException {
        try {
            Pageable pageable = PageRequest.of(page - 1, 12);
            Page<VehicleAccessory> result = repository.findByDealerId(dealerId, pageable);
            if(result.hasContent()){
                return result;
            }else{
                throw new ServiceException("No accessory found", HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getStatus());
        }
    }
    
}
