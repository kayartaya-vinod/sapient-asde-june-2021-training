package com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1317;

import java.util.List;

import com.sapient.asde.batch5.customerservice.entity.Vehicle;
import com.sapient.asde.batch5.customerservice.service.ServiceException;

public interface FavoriteVehicleService {
  public List<Vehicle> customerWishlist(String userId) throws ServiceException;
}
