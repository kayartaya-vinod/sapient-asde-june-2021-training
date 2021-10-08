package com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1313;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AverageRatingServiceTest {
    @Autowired
    AverageRatingService service;

    @MockBean
    FeedbackRepository dao;

    private final String VALID_ID = "VALID_ID";

    Vehicle v;
    Feedback f1;
    Feedback f2;
    Feedback f3;

    @BeforeEach
    void setup() {
        f1 = new Feedback();
        f1.setId("HAGJDS786");
        f1.setRating(3);
        f1.setReview("This is a review");
        f1.setVehicleId("Vednf123");
        f1.setUserId("Gjd1234");

        f2 = new Feedback();
        f2.setId("HAGJDS7862");
        f2.setRating(4);
        f2.setReview("This is another review");
        f2.setVehicleId("Vednf123");
        f2.setUserId("Gjd123487");

        Mockito.when(dao.getByVehicleId(VALID_ID)).thenReturn(Arrays.asList(f1, f2));
    }

    @Test
    void shouldGetAverageRating() throws ServiceException {
        Map<String, Object> p = service.getAverageRating(VALID_ID);
        assertNotNull(p);
    }

}
