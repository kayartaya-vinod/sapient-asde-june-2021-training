package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1681;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1681.DeleteVehicleAccessoryServiceImpl;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1681.Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class DeleteVehicleAccessoryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    RestTemplate template;

    @MockBean
    DeleteVehicleAccessoryServiceImpl service;

    @Autowired
    ObjectMapper om;

    @Autowired
    private DeleteVehicleAccessoryController controller;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    Map<String, Object> claims1;
    Map<String, Object> claims2;
    Map<String, Object> claims3;
    Map<String, Object> claims4;
    VehicleAccessory va;

    @BeforeEach
    void setup() throws RestClientException, JsonProcessingException, ServiceException {
        token = "token";
        claims1 = Utils.getClaims(true);
        claims2 = Utils.getClaims(false);
        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        log.info("coming Auth url is {} ", authVerifyUrl);
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));
        Mockito.when(service.deleteVehicleAccessoryById("invalid", "id"))
                .thenThrow(new ServiceException("INVALID VEHICLE ID", HttpStatus.NOT_FOUND));
    }

    @Test
    void contextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    void shouldReturnUnAuthorized_WhenDealerClaimsUnavailable() throws Exception {
        String url = "/api/vehicles/dealer/delete-accessory?id=1";
        mockMvc.perform(delete(url)).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnServiceException_WhenInvalidVehicleAccId() throws Exception {
        String url = "/api/vehicles/dealer/delete-accessory?id=invalid";
        mockMvc.perform(delete(url).header("Authorization", "jwt token")).andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnServiceException_WhenEnteredInvalidUserId() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        String url = "/api/vehicles/dealer/delete-accessory?id=valid";
        mockMvc.perform(delete(url).header("Authorization", "jwt token")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnSuccess_WhenValidUserIdAndVehicleAccId() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));
        String url = "/api/vehicles/dealer/delete-accessory?id=valid";
        Mockito.when(service.deleteVehicleAccessoryById("valid", "id")).thenReturn("valid");
        mockMvc.perform(delete(url).header("Authorization", "jwt token")).andExpect(status().isOk());
    }

}
