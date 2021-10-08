package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1151;


import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1151.StoreVehicleComparisonService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
/**
 * @author Neha Neha1@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc

 class StoreVehicleComparisonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestTemplate template;

    @MockBean
    StoreVehicleComparisonService service;

    @Autowired
    ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;

    private String token;
    
    Map<String, Object> storeVehicle;

    VehicleComparison storeVehicleObject;

    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException {
      token = "token";
     
      
    storeVehicle = new HashMap<>();
    storeVehicle=Utils.getStoreVehicle();

     storeVehicleObject = new VehicleComparison();
     storeVehicleObject=Utils.getStoreObject();

     Map<String, Object> claims = new HashMap<>();
     claims = Utils.getClaims();


    String url = String.format("%s/?jwt=%s", authVerifyUrl, token);
  
    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
    Mockito.when(service.storeVehicle(storeVehicleObject, "id")).thenReturn(storeVehicleObject);
  }


  @Test
  void testStoreVehicle() throws Exception {

   String url = "/api/vehicles/comparison";

   mockMvc.perform(post(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
       .content(om.writeValueAsString(storeVehicle))).andExpect(status().isOk());
 }

 @Test
 void shouldReturnUnAuthenticatedMessage_WhenStoreHeadersUnavailable() throws Exception {
   String url = "/api/vehicles/comparison";
   mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(
       storeVehicle)))
       .andExpect(status().isUnauthorized());
 }
    
}
