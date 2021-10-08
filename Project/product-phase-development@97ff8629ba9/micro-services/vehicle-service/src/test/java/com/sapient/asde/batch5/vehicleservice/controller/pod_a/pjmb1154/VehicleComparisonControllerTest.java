package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1154;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1154.VehicleComaprisonServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
 class VehicleComparisonControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  RestTemplate template;

  @MockBean
  VehicleComaprisonServiceImpl service;

  @Autowired
  ObjectMapper om;

  @Value("${authVerifyUrl}")
  String authVerifyUrl;
  
  String token;
  Map<String,Object> claims;
  VehicleComparison v1154;

  @BeforeEach
  void setup() throws RestClientException, JsonProcessingException, ServiceException{
    token = "token";
    claims = Utils.getClaims();
    v1154 = Utils.getComparisonMatrix();

    String url = String.format("%s/?jwt=%s", authVerifyUrl, token);
    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
    Mockito.when(service.getComparisonMatrix("1", "id")).thenReturn(v1154);
    Mockito.when(service.getComparisonMatrix("invalid", "id")).thenThrow(new ServiceException("INVALID ID", HttpStatus.NOT_FOUND));
  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Test
  void shouldReturnSuccessStatus_WhenMatrixIdExists() throws Exception {
    String url = "/api/vehicles/comparison/1";
    mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isOk());
  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Test
  void shouldReturnUnAuthenticatedMessage_WhenCustomerHeadersUnavailable() throws Exception {
    String url = "/api/vehicles/comparison/1";
    mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Test
  void shouldReturnServiceException_WhenInvalidId() throws Exception {
    String url = "/api/vehicles/comparison/invalid";
    mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isNotFound());
  }

}
