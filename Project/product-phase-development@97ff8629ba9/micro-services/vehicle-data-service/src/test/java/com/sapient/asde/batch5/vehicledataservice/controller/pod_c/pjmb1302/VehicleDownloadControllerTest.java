package com.sapient.asde.batch5.vehicledataservice.controller.pod_c.pjmb1302;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicledataservice.entity.VehicleDownload;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;
import com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1302.VehicleDownloadService;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author Yogeshwar Chaturvedi yogeshwar.chaturvedi@publicissapient.com
 */

@SpringBootTest
@AutoConfigureMockMvc
class VehicleDownloadControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RestTemplate template;

    @MockBean
    VehicleDownloadService service;

    @Autowired
    private ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;

    Writer writer;
    private String token;
    private String userId;

    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException {
        token = "token";
        userId = "user1";

        String url = String.format("%s/?jwt=%s", authVerifyUrl, token);

        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "customer");
        claims.put("exp", "324323");
        claims.put("userId", userId);
        claims.put("iat", "234234234");
        claims.put("email", "email");

        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

        VehicleDownload vehicle = new VehicleDownload();
        vehicle.setBrand("Maruthi");
        List<VehicleDownload> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        // Mockito.when(service.getAllVehicles(writer, "id")).thenThrow(new
        // IllegalStateException);
        Mockito.doNothing().when(service).getAllVehicles(writer, "id");
        Mockito.doThrow(new ServiceException()).when(service).getAllVehicles(writer, "invalid");
        List<String> ids = new ArrayList<>();
        ids.add("id");
        Mockito.doNothing().when(service).getVehiclesById(ids, writer);
        ids.add("id");
        Mockito.doThrow(new ServiceException()).when(service).getVehiclesById(ids, writer);

    }

    @Test
    void shouldCheckDownloadAll() {
        String url = "/api/data/download/csv/all";
        try {
            mockMvc.perform(get(url)).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckDownloadAllWithClaims() {
        String url = "/api/data/download/csv/all";
        try {
            mockMvc.perform(get(url).header("Authorization", "JWT token")).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckDownloadWithIdsWithClaimsDealer() throws RestClientException, JsonProcessingException {
        String url = "/api/data/download/csv/all";
        String url2 = String.format("%s/?jwt=%s", authVerifyUrl, token);

        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "dealer");
        claims.put("exp", "324323");
        claims.put("userId", userId);
        claims.put("iat", "234234234");
        claims.put("email", "email");

        Mockito.when(template.getForObject(url2, String.class)).thenReturn(om.writeValueAsString(claims));
        try {
            mockMvc.perform(get(url).header("Authorization", "JWT token")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckDownloadWithIdsWithClaimsInvalidDealer() throws RestClientException, JsonProcessingException {
        String url = "/api/data/download/csv/all";
        String url2 = String.format("%s/?jwt=%s", authVerifyUrl, token);

        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "dealer");
        claims.put("exp", "324323");
        claims.put("userId", "invalid");
        claims.put("iat", "234234234");
        claims.put("email", "email");

        Mockito.when(template.getForObject(url2, String.class)).thenReturn(om.writeValueAsString(claims));
        try {
            mockMvc.perform(get(url).header("Authorization", "JWT token")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckDownloadWithIds() {
        String url = "/api/data/download/csv?ids=1";
        try {
            mockMvc.perform(get(url)).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckDownloadWithIdsWithClaims() {
        String url = "/api/data/download/csv?ids=1";
        try {
            mockMvc.perform(get(url).header("Authorization", "JWT token")).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckDownloadAllWithClaimsDealer() throws RestClientException, JsonProcessingException {
        String url = "/api/data/download/csv?ids=1";
        String url2 = String.format("%s/?jwt=%s", authVerifyUrl, token);

        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "dealer");
        claims.put("exp", "324323");
        claims.put("userId", userId);
        claims.put("iat", "234234234");
        claims.put("email", "email");

        Mockito.when(template.getForObject(url2, String.class)).thenReturn(om.writeValueAsString(claims));
        try {
            mockMvc.perform(get(url).header("Authorization", "JWT token")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckDownloadAllWithInvalid() throws RestClientException, JsonProcessingException {
        String url = "/api/data/download/csv?ids=id,id";
        String url2 = String.format("%s/?jwt=%s", authVerifyUrl, token);

        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "dealer");
        claims.put("exp", "324323");
        claims.put("userId", userId);
        claims.put("iat", "234234234");
        claims.put("email", "email");

        Mockito.when(template.getForObject(url2, String.class)).thenReturn(om.writeValueAsString(claims));
        try {
            mockMvc.perform(get(url).header("Authorization", "JWT token")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
