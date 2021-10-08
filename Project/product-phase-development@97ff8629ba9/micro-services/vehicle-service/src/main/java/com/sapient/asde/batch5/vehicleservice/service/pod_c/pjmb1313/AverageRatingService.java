package com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1313;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Jay
 */
@Service
public class AverageRatingService {

    @Autowired
    FeedbackRepository dao;

    public Map<String, Object> getAverageRating(String vehicleId) {
        List<Feedback> feedbacks = dao.getByVehicleId(vehicleId);
        Map<String, Object> mp = new HashMap<>();
        Integer count = 0;
        Double averageRating = 0.0;
        for (Feedback feedback : feedbacks) {
                if (feedback.getRating() >=0) {
                    count+=1;
                    averageRating += feedback.getRating();
                }
        }
            if(count>0){averageRating /= count;
                averageRating=(Math.round(averageRating*100)/(double)100);
            }
        mp.put("averageRating", averageRating);
        mp.put("totalCustomer", count);
        return mp;
    }

}
