package com.sapient.asde.batch5.vehicleservice.controller.pod_c.pjmb1313;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1313.AverageRatingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
 class GetAverageRatingControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AverageRatingService service;

  private final String VALID_ID = "VALID_ID";
  Vehicle v;
  Vehicle v2;

  @BeforeEach
  void setup() throws ServiceException {
    Map<String, Object> mp = new HashMap<>();
    mp.put("averageRating", 3.0);
    mp.put("totalCustomer", 200);
    Mockito.when(service.getAverageRating(VALID_ID)).thenReturn((mp));
  }

  @Test
  void shouldGiveStatusOkForValidId() throws Exception {
    String url = "/api/vehicles/average-rating/" + VALID_ID;
    mockMvc.perform(get(url)).andExpect(status().isOk());
  }
}
