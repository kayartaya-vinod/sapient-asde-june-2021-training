package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1677;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1677.ReviewRatingForDealerUploadsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Deepthi.S deepthi.s@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class ReviewRatingForDealerUploadsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReviewRatingForDealerUploadsService service;

    @MockBean
    RestTemplate template;

    @Autowired
    ObjectMapper om;
    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    Map<String, Object> claims;
    Map<String, Object> claims2;
    Map<String, Object> feedbacks;

    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException {
        token = "token";
        claims = Utils.getClaims(true);
        claims2 = Utils.getClaims(false);
        feedbacks = Utils.getFeedbacks();
        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        Mockito.when(service.getFeedbackForDealerUploads("dealerId", 1)).thenReturn(feedbacks);
    }

    @Test
    void shouldReturnSuccessStatus_WhenVehicleExists() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

        String url = "/api/vehicles/dealer/feedbacks";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isOk());

    }

    @Test
    void shouldReturnUnAuthenticatedMessage_WhenDealerHeaderUnavailable() throws Exception {
        String url = "/api/vehicles/dealer/feedbacks";
        mockMvc.perform(get(url)).andExpect(status().isUnauthorized());

    }

    @Test
    void shoulReturnServiceExcpetion_WhenUserTypeIsNotDealer() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        String url = "/api/vehicles/dealer/feedbacks";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isUnauthorized());
    }
}
