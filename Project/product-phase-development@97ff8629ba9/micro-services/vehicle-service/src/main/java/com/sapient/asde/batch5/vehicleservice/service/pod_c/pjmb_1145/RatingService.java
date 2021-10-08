package com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb_1145;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mritunjay Kumar Jay mritunjay.jay@publicissapient.com
 */


@Component
public class RatingService {
    @Autowired
    FeedbackRepository dao;

    public Feedback addRating(String vehicleId, String userId, Integer rating) {
        Feedback feedback = dao.getByVehicleIdAndUserId(vehicleId, userId);
        Feedback feedbackObject = new Feedback();
        feedbackObject.setRating(rating);
        feedbackObject.setVehicleId(vehicleId);
        feedbackObject.setUserId(userId);
        if (feedback == null) {
            dao.insert(feedbackObject);
            return feedbackObject;
        } else {
            feedback.setRating(rating);
            dao.save(feedback);
            return feedback;
        }
    }

    public Integer getRating(String vehicleId, String userId) {
        Feedback feedback = dao.getByVehicleIdAndUserId(vehicleId, userId);
        if(feedback==null)
            return 0;
        return feedback.getRating();
    }

    
}
