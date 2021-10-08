package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1527;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@SpringBootTest
class VehicleComparisonWithIdsServiceTest {
    
  @Autowired
  VehicleComparisonWithIdsServiceImpl service;

  @MockBean
  VehicleRepository repo;

  @MockBean
  AttributesProperties properties;

  @BeforeEach
  void setup() throws ServiceException{
    Map<String, List<String>> map = new LinkedHashMap<>();
    
     // Grouping the attributes.
     map.put("basicInformation", Arrays.asList("brand", "model", "price", "vehicleType", "year", "fuelType","airConditioning"));
     map.put("performance", Arrays.asList("topSpeedLimit", "unitsFuelConsumption", "tankCapacity", "wheelSpeed", "ignitionTime", "accelaration", "powerTrainTorque", "batteryLevel"));
     map.put("safety", Arrays.asList("theftAlarm", "airBagCount", "nightMode", "childSafetyLock", "malfunctionIndicator", "isABS"));
      
    List<String> ids = Arrays.asList("id1");
    Mockito.when(repo.findAllById(ids)).thenReturn(Utils.getVehicles());
    Mockito.when(properties.getAttributes()).thenReturn(map);
  }

  @Test
  void shouldReturnMatrix() throws ServiceException, JsonMappingException, JsonProcessingException {
    List<String> ids = Arrays.asList("id1");

    Map<String, Object> actual = new HashMap<>();
    actual = service.getVehiclesById(ids);
    assertNotNull(actual);
  }
}
