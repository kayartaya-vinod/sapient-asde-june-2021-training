package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1679;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1679.GetAccesssoryListByDealerIdService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
/**
 * @author Manvendra Singh
 */
@SpringBootTest
@AutoConfigureMockMvc
class GetAccessoryListByDealerIdControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    RestTemplate template;

    @MockBean
    GetAccesssoryListByDealerIdService service;

    @Autowired
    ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    Map<String, Object> claims;
    Map<String, Object> claims2;
    Page<VehicleAccessory> list;

    @BeforeEach
    void setup() throws RestClientException, JsonProcessingException, ServiceException {
        token = "token";
        claims = Utils.getClaims(true);
        claims2 = Utils.getClaims(false);
        list = Utils.getAccessoryList();
        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        Mockito.when(service.getAccessoryListByDealerId("invalid",1))
                .thenThrow(new ServiceException("INVALID ID", HttpStatus.NOT_FOUND));
    }

    @Test
    void shouldGiveUnautherizedForNoHeaders() throws Exception {
        String url = "/api/vehicles/dealer/accessories?page=1";
        mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldGiveStatusOKforValidDealer() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
        Mockito.when(service.getAccessoryListByDealerId("id",1)).thenReturn(list);
        String url = "/api/vehicles/dealer/accessories?page=1";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isOk());
    }

    @Test
    void shouldThrowExceptionForNotADealer() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        String url = "/api/vehicles/dealer/accessories?page=1";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isUnauthorized());
    }

}
