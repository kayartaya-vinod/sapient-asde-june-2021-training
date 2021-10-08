//@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1147;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("pjmb1147_vehicleReviewServiceImpl")
public class VehicleReviewServiceImpl implements VehicleReviewService {
    @Autowired
    VehicleRepository vehicleRepo;

    @Autowired
    FeedbackRepository feedbackRepo;

    @Override
    public Feedback saveReview(String userId, String vehicleId, String review) throws ServiceException {
        Feedback feedback;
        
        if (userId == null || vehicleId == null) {
            throw new ServiceException("Invalid input for userId or VehicleId");
        }

        feedback = feedbackRepo.findByVehicleIdUserId(vehicleId, userId);
        if (feedback == null) {
            feedback=new Feedback();
            feedback.setVehicleId(vehicleId);
            feedback.setUserId(userId);
            feedback.setReview(review);
            feedbackRepo.save(feedback);
        }
        else {
            feedback.setReview(review);
            feedbackRepo.save(feedback);
        }
        return feedback;
    }

    @Override
    public String getReview(String userId, String vehicleId) throws ServiceException {
        Feedback feedback;
        if (userId == null || vehicleId == null) {
            throw new ServiceException("Invalid input for userId or VehicleId");
        }
        
        feedback = feedbackRepo.findByVehicleIdUserId(vehicleId, userId);
        if (feedback == null) {
            return "";
        }
        else {
            String review = feedback.getReview();
            if (review == null || review.equals("")) return "";
            return review;
        }
    }
}
