package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1303;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1303.DeleteVehicleServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class DeleteVehicleControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    RestTemplate template;

    @MockBean
    DeleteVehicleServiceImpl deleteVehicleService;

    @Autowired
    ObjectMapper om;

    @Autowired
    private DeleteVehicleController deleteVehicleController;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    Map<String, Object> claims1;
    Map<String, Object> claims2;
    Map<String, Object> claims3;
    Map<String, Object> claims4;
    Vehicle v1156;
    VehicleComparison vComparison;

  
    @BeforeEach
    void setup() throws RestClientException, JsonProcessingException, ServiceException {
        token = "token";
        claims1 = Utils.getClaims(true);
        claims2 = Utils.getClaims(false);
        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        log.info("coming Auth url is {} ", authVerifyUrl);
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));
        Mockito.when(deleteVehicleService.deleteVehicleById("invalid","id"))
                .thenThrow(new ServiceException("INVALID VEHICLE ID", HttpStatus.NOT_FOUND));
    }

    @Test
	void contextLoads() throws Exception {
		assertNotNull(deleteVehicleController);
	}

    @Test
    void shouldReturnUnAuthenticatedMessage_WhenDealerHeadersUnavailable() throws Exception {
        String url = "/api/vehicles/dealer?ids=1";
        mockMvc.perform(delete(url)).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnServiceException_WhenInvalidVehicleId() throws Exception {
        String url = "/api/vehicles/dealer?ids=invalid";
        mockMvc.perform(delete(url).header("Authorization", "jwt token")).andExpect(status().isNotFound());
    }
    
    @Test
    void shouldReturnServiceException_WhenInvalidUserId() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        String url = "/api/vehicles/dealer?ids=valid";
        mockMvc.perform(delete(url).header("Authorization", "jwt token")).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnSuccess_WhenValidUserIdAndVehicleId() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));
        String url = "/api/vehicles/dealer?ids=valid";
        Mockito.when(deleteVehicleService.deleteVehicleById("valid","id"))
        .thenReturn("valid");
        mockMvc.perform(delete(url).header("Authorization", "jwt token")).andExpect(status().isOk());
    }

}
