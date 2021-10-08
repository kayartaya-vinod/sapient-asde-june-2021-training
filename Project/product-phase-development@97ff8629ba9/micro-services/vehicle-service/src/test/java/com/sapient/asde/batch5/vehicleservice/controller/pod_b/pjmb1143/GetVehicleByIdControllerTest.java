package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1143;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143.GetVehicleByIdService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
 class GetVehicleByIdControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GetVehicleByIdService service;

  private final String VALID_ID = "VALID_ID";
  private final String INVALID_ID = "INVALID_ID";
  Vehicle v;
  Vehicle v2;

  @BeforeEach
  void setup() throws ServiceException {
    v = new Vehicle();
    v.setDealerId("dealerId");
    v.setId("id");

    Mockito.when(service.getVehicleById(VALID_ID)).thenReturn((v));
    Mockito.when(service.getVehicleById(INVALID_ID)).thenThrow(new ServiceException("Id not valid ", HttpStatus.NOT_FOUND));   
  }

  @Test
  void shouldGiveStatusOkForValidId() throws Exception {
    String url = "/api/vehicles/" + VALID_ID;
    mockMvc.perform(get(url)).andExpect(status().isOk());
  }

  @Test
  void shouldStatusNotFoundForInvalidId() throws Exception {
    String url = "/api/vehicles/" + INVALID_ID;
    mockMvc.perform(get(url)).andExpect(status().isNotFound());
  }
}
