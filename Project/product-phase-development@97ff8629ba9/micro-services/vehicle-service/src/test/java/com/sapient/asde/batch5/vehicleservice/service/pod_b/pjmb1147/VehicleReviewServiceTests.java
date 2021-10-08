//@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1147;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class VehicleReviewServiceTests {
    @Autowired
	VehicleReviewService service;

	@MockBean
	FeedbackRepository repo;
    
    Feedback feedback , feedbackWithNullReview , feedbackWithEmptyReview , nullFeedback , anotherFeedback;
    String review;
    String emptyReview;
    String nextReview;
    String vehicleId;
    String userId;
    String otherUserId;
    String otherVehicleId;
    String secondUserId;
    String secondVehicleId;
    String vehicleIdForNull;
    String userIdForNull;
    String randomUserId;
    String randomVehicleId;
    String vehicleIdNull;
    String userIdNull;
    
    @BeforeEach
    void setup() {
        userId = "userId";
        review = "review";
        vehicleId = "vehicleId";
        otherUserId = "otherUser";
        otherVehicleId = "otherVehicle";
        secondUserId = "secondUserId";
        secondVehicleId = "secondVehicleId";
        emptyReview = "";
        vehicleIdForNull = "v123";
        userIdForNull = "u123";
        nextReview = "randomReview";
        randomUserId = "randomUserId";
        randomVehicleId = "randomVehicleId";
        vehicleIdNull = null;
        userIdNull = null;

        feedback = new Feedback();
        feedbackWithNullReview = new Feedback();
        feedbackWithEmptyReview = new Feedback();
        nullFeedback = null;
        anotherFeedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setReview(review);
        feedback.setVehicleId(vehicleId);

        feedbackWithNullReview.setUserId(otherUserId);
        feedbackWithEmptyReview.setVehicleId(otherVehicleId);

        feedbackWithEmptyReview.setUserId(secondUserId);
        feedbackWithEmptyReview.setVehicleId(secondVehicleId);
        feedbackWithEmptyReview.setReview(emptyReview);


        Mockito.when(repo.findByVehicleIdUserId(vehicleId, userId)).thenReturn(feedback);
        Mockito.when(repo.findByVehicleIdUserId(otherVehicleId , otherUserId)).thenReturn(feedbackWithNullReview);
        Mockito.when(repo.findByVehicleIdUserId(secondVehicleId , secondUserId)).thenReturn(feedbackWithEmptyReview);
        Mockito.when(repo.findByVehicleIdUserId(vehicleIdForNull , userIdForNull)).thenReturn(nullFeedback);
        Mockito.when(repo.findByVehicleIdUserId(randomVehicleId, randomUserId)).thenReturn(nullFeedback);
    }

    @Test
    void shouldUpdateReview() throws ServiceException {
        String expected = review;
        String actual = service.saveReview(userId , vehicleId , review).getReview();
        log.info("expected = {}" , expected);
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetReview() throws ServiceException {
        String expected = review;
        String actual = service.getReview(userId, vehicleId);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyStringIfReviewIsNull() throws ServiceException {
        String expected = "";
        String actual = service.getReview(otherUserId , otherVehicleId);
        assertEquals(expected , actual);
    }

    @Test
    void shouldReturnEmptyStringIfReviewIsEmptyString() throws ServiceException {
        String expected = "";
        String actual = service.getReview(secondUserId , secondVehicleId);
        assertEquals(expected , actual);
    }

    @Test
    void shouldSaveNewReview() throws ServiceException {
        Feedback actual = service.saveReview(userIdForNull, vehicleIdForNull, nextReview);
        assertNotNull(actual);
    }

    @Test
    void shouldReturnEmptyStringWhenFeedbackIsNull() throws ServiceException {
        String expected = "";
        String actual = service.getReview(randomUserId, randomVehicleId);
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWithNullParams() {
        assertThrows(ServiceException.class, () -> service.getReview(userIdNull , vehicleIdNull));
        assertThrows(ServiceException.class, () -> service.getReview(userId , vehicleIdNull));
        assertThrows(ServiceException.class, () -> service.getReview(userIdNull ,vehicleId));
    }

    @Test
    void shouldThrowExceptionOnSavingNullParams() {
        assertThrows(ServiceException.class , () -> service.saveReview(userIdNull , vehicleIdNull , review));
        assertThrows(ServiceException.class , () -> service.saveReview(userId , vehicleIdNull , review));
        assertThrows(ServiceException.class , () -> service.saveReview(userIdNull , vehicleId , review));
    }
}
