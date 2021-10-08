package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1156;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1154.VehicleComaprisonServiceImpl;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143.GetVehicleByIdServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
class VehicleComaprisonDownloadControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RestTemplate template;

    @MockBean
    VehicleComaprisonServiceImpl service;

    @MockBean
    GetVehicleByIdServiceImpl getVehicleByIdServiceImpl;

    @MockBean
    VehicleComparison vc;

    @Autowired
    ObjectMapper om;

    @Autowired
    private VehicleComaprisonDownloadController vehicleComaprisonDownloadController;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    Map<String, Object> claims;
    Map<String, Object> claims2;
    Vehicle v1156;
    VehicleComparison vComparison;

    @BeforeEach
    void setup() throws RestClientException, JsonProcessingException, ServiceException {
        token = "token";
        claims = Utils.getClaims(true);
        claims2 = Utils.getClaims(false);
        v1156 = Utils.getVehicle();
        vComparison =Utils.getVehicleComparison();
        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        log.info("coming Authurl is {} ", authVerifyUrl);
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        Mockito.when(service.getComparisonMatrix("id","invalid"))
                .thenThrow(new ServiceException("INVALID ID", HttpStatus.NOT_FOUND));
    }

    @Test
	void contextLoads() throws Exception {
		assertNotNull(vehicleComaprisonDownloadController);
	}

    @Test
    void shouldReturnUnAuthenticatedMessage_WhenCustomerHeadersUnavailable() throws Exception {
        String url = "/api/vehicles/comparison/download/valid";
        mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnServiceException_WhenInvalidId() throws Exception {
        String url = "/api/vehicles/comparison/download/invalid";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnSuccess_WhenValidId() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
        Mockito.when(service.getComparisonMatrix("valid","id")).thenReturn(vComparison);
        Mockito.when(getVehicleByIdServiceImpl.getVehicleById("1")).thenReturn(v1156);
        String url = "/api/vehicles/comparison/download/valid";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isOk());
    }



    
    
}
