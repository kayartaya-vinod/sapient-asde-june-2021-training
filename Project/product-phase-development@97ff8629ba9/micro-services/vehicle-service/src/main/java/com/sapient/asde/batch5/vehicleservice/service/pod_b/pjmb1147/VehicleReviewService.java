//@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1147;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;


public interface VehicleReviewService {
       public Feedback saveReview(String userId , String vehicleId , String review) throws ServiceException;
       public String getReview(String userId , String vehicleId) throws ServiceException;
}
