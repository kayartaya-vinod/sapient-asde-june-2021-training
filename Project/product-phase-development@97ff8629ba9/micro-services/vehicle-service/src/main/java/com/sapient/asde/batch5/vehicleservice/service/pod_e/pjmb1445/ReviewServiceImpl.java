package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1445;


import com.sapient.asde.batch5.vehicleservice.repository.ReviewRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ReviewServiceImpl implements ReviewService {

   
    @Autowired
    ReviewRepository repo;
   

    @Override
    public String[][] getfeedbacks(String vid) throws ServiceException {
       
        return repo.getArray(vid);
    }
}
