package com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1145;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb_1145.RatingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class RatingServiceTest {
    @Autowired
    RatingService service;

    @MockBean
    FeedbackRepository repo;
    private String vehicleId="car1";
    private String userId="user1";
    private Integer rating = 5;
    private Feedback mockfeedback;
    @BeforeEach
    void setup() throws ServiceException {
        mockfeedback = new Feedback();
        mockfeedback.setUserId(userId);
        mockfeedback.setVehicleId(vehicleId);
        mockfeedback.setRating(rating);
        Mockito.when(repo.getByVehicleIdAndUserId(vehicleId, userId)).thenReturn(mockfeedback);
        Mockito.when(repo.getByVehicleIdAndUserId("vehicle1", "user1")).thenReturn(null);
    }

    @Test
    void addRatingTest() throws ServiceException {
        try{
            assertEquals(service.addRating(vehicleId, userId, rating), mockfeedback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRatingTest() throws ServiceException {
        try{assertEquals(service.getRating(vehicleId, userId), rating);}catch(Exception e){e.printStackTrace();}
    }
    @Test
    void getRatingTestForUnavailableData() throws ServiceException {
        try{assertEquals(0,service.getRating("vehicle1", "user1"));}catch(Exception e){e.printStackTrace();}
    }
    @Test
    void addRatingTestForUnavailableData() throws ServiceException {
        Feedback mockNullFeedback = new Feedback();
        mockNullFeedback.setRating(rating);
        mockNullFeedback.setUserId("user1");
        mockNullFeedback.setVehicleId("vehicle1");
        try{assertEquals(mockNullFeedback,service.addRating("vehicle1", "user1",rating));}catch(Exception e){e.printStackTrace();}
    }
}
