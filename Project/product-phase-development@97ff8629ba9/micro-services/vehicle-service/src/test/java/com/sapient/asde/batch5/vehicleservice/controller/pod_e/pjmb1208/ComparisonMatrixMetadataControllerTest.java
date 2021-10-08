package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1208;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1208.ComparisonMatrixMetadataServiceImpl;

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
/**
 * @author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
 */
class ComparisonMatrixMetadataControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RestTemplate template;

    @MockBean
    ComparisonMatrixMetadataServiceImpl service;

    @Autowired
    ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    Map<String, Object> claims;
    Map<String, Object> claims2;
    List<VehicleComparison> v1208;

    @BeforeEach
    void setup() throws RestClientException, JsonProcessingException, ServiceException {
        token = "token";
        claims = Utils.getClaims(true);
        claims2 = Utils.getClaims(false);
        v1208 = Utils.getMatrixMetadata();

        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        log.info("coming Authurl is {} ", authVerifyUrl);

        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        Mockito.when(service.getMetadataByUserId("invalid"))
                .thenThrow(new ServiceException("INVALID ID", HttpStatus.NOT_FOUND));
    }

    @Test
    void shouldReturnSuccessStatus_WhenMatrixMetadataExists() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
        Mockito.when(service.getMetadataByUserId("id")).thenReturn(v1208);
        String url = "/api/vehicles/matrix-metadata";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnUnAuthenticatedMessage_WhenCustomerHeadersUnavailable() throws Exception {
        String url = "/api/vehicles/matrix-metadata";
        mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnServiceException_WhenInvalidId() throws Exception {
        String url = "/api/vehicles/matrix-metadata";
        mockMvc.perform(get(url).header("Authorization", "jwt token")).andExpect(status().isNotFound());
    }

}
