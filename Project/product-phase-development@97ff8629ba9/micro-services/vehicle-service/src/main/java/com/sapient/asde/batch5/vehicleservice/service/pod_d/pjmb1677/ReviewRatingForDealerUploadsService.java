package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1677;

import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

/**
 * @Author Deepthi.S deepthi.s@publicissapient.com
 */
public interface ReviewRatingForDealerUploadsService {
    Map<String, Object> getFeedbackForDealerUploads(String dealerId,Integer page) throws ServiceException;
}
