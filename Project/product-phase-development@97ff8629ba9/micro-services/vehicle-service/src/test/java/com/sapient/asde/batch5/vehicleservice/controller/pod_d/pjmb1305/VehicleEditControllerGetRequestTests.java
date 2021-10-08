package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1305;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1305.VehicleEditService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Suraj kumar suraj.kumar3@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class VehicleEditControllerGetRequestTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    VehicleEditService service;

    @MockBean
    RestTemplate template;

    @Autowired
    ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    Map<String, Object> claims;
    Map<String, Object> claims2;
    Vehicle vehicle;


    @BeforeEach
    void setup() throws ServiceException,RestClientException,JsonProcessingException{
        token ="token";
        claims = Utils.getClaims(true);
        claims2 = Utils.getClaims(false);
        vehicle=Utils.getVehicle();
        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        log.info("coming Authurl is {} ", authVerifyUrl);
        Mockito.when(service.getVehicle("vehicleId", "id")).thenReturn(vehicle);
    }

    @Test
    void shouldReturnSuccessStatus_WhenVehicleExists() throws Exception{
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
        
        String url="/api/vehicles/dealer/vehicleId";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isOk());


    }
    @Test
    void shouldReturnUnAuthenticatedMessage_WhenDealerHeaderUnavailable() throws Exception{
        String url="/api/vehicles/dealer/vehicleId";
        mockMvc.perform(get(url)).andExpect(status().isUnauthorized());

    }

    @Test
    void shoulReturnServiceExcpetion_WhenUserTypeIsNotDealer() throws Exception{
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        String url ="/api/vehicles/dealer/vehicleId";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isUnauthorized());
    }
    

    
}
