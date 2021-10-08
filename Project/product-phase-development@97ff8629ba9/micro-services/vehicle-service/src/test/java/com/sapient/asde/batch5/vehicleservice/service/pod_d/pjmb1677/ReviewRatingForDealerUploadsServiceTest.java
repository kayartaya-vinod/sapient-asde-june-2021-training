package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1677;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Deepthi.S deepthi.s@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class ReviewRatingForDealerUploadsServiceTest {

    @Autowired
    ReviewRatingForDealerUploadsService service;

    @MockBean
    FeedbackRepository fbrepo;

    @MockBean
    VehicleRepository vrepo;

    @MockBean
    RestTemplate template;

    @Autowired
    ObjectMapper om;

    @Value("${custUrl}")
    String custUrl;
    String url;
    private List<Vehicle> vehicles;
    private Vehicle v;
    private List<String> vids = new ArrayList<String>(List.of("1", "2"));
    private String dealerId;
    private String dealerIdWithNoVehicles;
    private String invalidDealerId;

    Pageable pageable = PageRequest.of(0, 12);
    Page<Feedback> fbPage;

    @BeforeEach
    void setup() throws ServiceException {
        dealerId = "dealerId";
        invalidDealerId = "invalidDealerId";
        dealerIdWithNoVehicles = "dealerIdWithNoVehicles";
        vehicles = Utils.getVehicles();
        
        v = new Vehicle();
        v.setId("1");
        v.setBrand("bmw");
        v.setModel("bmw x100");
        v.setVehicleType("Sedan");
        v.setDealerId("dealerId");
        fbPage = Utils.getFeedbacks();
        Mockito.when(vrepo.findVehiclesByDealerId(dealerId)).thenReturn(vehicles);
        Mockito.when(vrepo.findVehiclesByDealerId(dealerIdWithNoVehicles)).thenReturn(Collections.emptyList());
        Mockito.when(vrepo.findVehiclesByDealerId(invalidDealerId)).thenReturn(Collections.emptyList());
        Mockito.when(template.getForObject(custUrl + "userId1", String.class)).thenReturn("customerName1");
        Mockito.when(template.getForObject(custUrl + "userId2", String.class)).thenReturn("customerName2");
        Mockito.when(vrepo.findById("1")).thenReturn(Optional.of(v));
        Mockito.when(vrepo.findById("2")).thenReturn(Optional.of(v));
        Mockito.when(fbrepo.findAllByVehicleIds(vids, pageable)).thenReturn(fbPage);
    }

    @Test
    void contextLoads() throws Exception {
        assertNotNull(service);
    }

    @Test
    void shouldReturnFeedbacks_whenDealerId() throws ServiceException {
        assertDoesNotThrow(() -> service.getFeedbackForDealerUploads(dealerId, 1));
    }

    @Test
    void shouldThrowServiceException_whenDealerIdIsEmpty() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.getFeedbackForDealerUploads("",1));
        assertThrows(ServiceException.class, () -> service.getFeedbackForDealerUploads(null,1));
       
    }
    
    @Test
    void shouldThrowServiceException_whenDealerIdHasNoVehicles() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.getFeedbackForDealerUploads(dealerIdWithNoVehicles, 1));
    }

}
