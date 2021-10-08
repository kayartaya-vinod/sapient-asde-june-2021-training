package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1445;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

public interface ReviewService {
    public String[][] getfeedbacks(String vid) throws ServiceException;
}
