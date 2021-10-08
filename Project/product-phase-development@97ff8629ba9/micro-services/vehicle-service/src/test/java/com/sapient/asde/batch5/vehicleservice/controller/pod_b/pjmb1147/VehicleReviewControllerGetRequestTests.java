//@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1147;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1147.VehicleReviewService;

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

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class VehicleReviewControllerGetRequestTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VehicleReviewService service;

    @MockBean
    RestTemplate template;

    @Autowired
    ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;

    String token;
    String errorToken;
    String validUserId;
    String invalidUserId;
    String baseUrl;
    String validVehicleId;
    String invalidVehicleId;
    String review;

    Map<String , Object> claims;
    Feedback feedback;

    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException {
        token = "randomToken1234";
        baseUrl = "/api/vehicles/";
        validVehicleId = "vehicle123";
        invalidVehicleId = "vehicle321";
        claims = Utils.getClaims();
        validUserId = (String)claims.get("userId");
        review = "This is a overwritten review by same user";
        String url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        feedback = new Feedback();
        feedback.setUserId(validUserId);
        feedback.setReview(review);
        feedback.setVehicleId(validVehicleId);
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
        Mockito.when(service.getReview(validUserId , validUserId)).thenReturn(review);
    }

    @Test
    void shouldGiveStatusOkWithCorrectToken() throws Exception {
        String url = baseUrl + validVehicleId + "/reviews";
        log.info("URL = {}" , url);
        mockMvc.perform(get(url).header("Authorization", "JWT " + token)).andExpect(status().isOk());
    }

    @Test
    void shouldGiveUnauthorizedStatusWithoutTokenInAuthorizationHeader() throws Exception {
        String url = baseUrl + validVehicleId + "/reviews";
        log.info("URL = {}" , url);
        mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
    }

}
