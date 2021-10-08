package com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1315;

import java.util.List;

import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.repository.CustomerRepository;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;
import com.sapient.asde.batch5.customerservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Rajgaria, abhishek.rajgaria@publicissapient.com
 */

@Slf4j
@Service
public class UpdateWishListServiceImpl implements UpdateWishListService {
    @Autowired
    CustomerRepository custRepo;
    
    @Autowired
    UserRepository userRepo;

    @Override
    public List<String> updateWishList(String userId, String vehicleId) throws ServiceException {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            log.info("updateWishList  - Vehicle id not supplied");
            throw new ServiceException("Vehicle id cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        if (userId == null || userId.trim().isEmpty()) {
            log.info("updateWishList userId not supplied. ");
            throw new ServiceException("User id cannot be empty.", HttpStatus.BAD_REQUEST);
        }
        log.info("userId = {}, vehicle id = {}",userId, vehicleId);
        try{

            Customer customer = custRepo.findByUserId(userId);
            if(customer==null){
                log.info("updateWishList - Customer is invalid");
                throw new ServiceException("Unauthorized Customer ", HttpStatus.NOT_FOUND);
            }
            List<String> ids = customer.getWishlist();
            log.info("Fetched wish List ids = {}", ids);
            if(ids.contains(vehicleId)){
                ids.remove(vehicleId);
            }
            else{
                ids.add(vehicleId);
            }
            log.info("Updated wish List ids = {}", ids);
            customer.setWishlist(ids);
            custRepo.save(customer);
            return ids;
        }
        catch(ServiceException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
