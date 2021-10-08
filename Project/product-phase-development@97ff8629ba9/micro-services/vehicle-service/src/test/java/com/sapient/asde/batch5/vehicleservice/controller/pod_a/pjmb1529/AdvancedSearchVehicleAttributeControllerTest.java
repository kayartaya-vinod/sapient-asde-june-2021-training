package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1529;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1529.AdvancedSearchVehicleAttributeSerice;

import org.bson.json.JsonObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AdvancedSearchVehicleAttributeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  @Qualifier("pjmb1529_advancedsearchvehicleattributeserviceimpl")
  private AdvancedSearchVehicleAttributeSerice service;

  @Test
  void shouldReturnOk_whenAttributesAreRequested() throws Exception {
    JsonObject jb = new JsonObject("{\"attribute\": \"brand\"}");
    Mockito.when(service.getAdvancedSearchAttributes()).thenReturn(Arrays.asList(jb));

    String url = "/api/vehicles/search-attributes";

    mockMvc.perform(get(url)).andExpect(status().isOk());
  }
}
