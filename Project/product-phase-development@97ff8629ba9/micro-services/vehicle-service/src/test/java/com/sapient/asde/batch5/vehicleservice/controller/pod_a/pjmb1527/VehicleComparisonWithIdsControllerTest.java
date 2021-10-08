package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1527;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1527.VehicleComparisonWithIdsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class VehicleComparisonWithIdsControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  VehicleComparisonWithIdsService service;

  @BeforeEach
  void setup() throws RestClientException, ServiceException, JsonMappingException, JsonProcessingException{
    
    List<String> ids = Arrays.asList("id1", "id2");
    List<String> ids2 = Arrays.asList("id1", "id2", "id3", "id4", "id5");
    Mockito.when(service.getVehiclesById(ids)).thenReturn(Utils.getMatrix());
    Mockito.when(service.getVehiclesById(ids2)).thenThrow(new ServiceException());
  }

 /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Test
  void shouldReturnSuccessStatus_WhenSuccess() throws Exception {
    String url = "/api/vehicles/compare/?ids=id1,id2";
    mockMvc.perform(get(url)).andExpect(status().isOk());


    String url2 = "/api/vehicles/compare/?ids=id1,id2,id3,id4,id5";
    mockMvc.perform(get(url2)).andExpect(status().isBadRequest());
  }

}
