package com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1317;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.entity.Vehicle;
import com.sapient.asde.batch5.customerservice.repository.CustomerRepository;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;
import com.sapient.asde.batch5.customerservice.repository.VehicleRepository;
import com.sapient.asde.batch5.customerservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FavoriteVehicleServiceImpl implements FavoriteVehicleService {

  @Autowired
  CustomerRepository cutomerRepo;

  @Autowired
  UserRepository userRepo;

  
  @Autowired
  CustomerRepository customerRepo;

  @Autowired
  TypeReference<HashMap<String, Object>> typeRef;

  @Autowired
  VehicleRepository vr;
 

  
  /**
   * @author vikash kumar gupta
   */
  @Override
  public List<Vehicle> customerWishlist(String userId) throws ServiceException {
    
    Customer c;
    try {
      
      c= customerRepo.findByUserId(userId);
    } catch (Exception e) {

      throw new ServiceException(e);
    }
    List<String> ids=c.getWishlist();    
    List<Vehicle> lst=new ArrayList<>();
    for(String id: ids)
    {

      Optional<Vehicle> v;
      try {
        v=vr.findById(id);
        if(v.isPresent()){
          lst.add(v.get());
        }
      } catch (Exception e) {
        throw new ServiceException(e);
      }

    }
    return lst;
    
  }


}
