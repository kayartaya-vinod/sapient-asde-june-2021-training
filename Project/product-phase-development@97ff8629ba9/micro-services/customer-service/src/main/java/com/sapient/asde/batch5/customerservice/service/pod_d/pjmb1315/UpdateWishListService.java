package com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1315;

import java.util.List;

import com.sapient.asde.batch5.customerservice.service.ServiceException;

/**
 * @author Abhishek Rajgaria, abhishek.rajgaria@publicissapient.com
 */
public interface UpdateWishListService {
    public List<String> updateWishList(String userId, String vehicleId) throws ServiceException;
}
