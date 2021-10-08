/**
 * @author Rohit Bhatt rohit.bhatt1@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1683;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1683.AddVehicleAccessoryService;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
class AddVehicleAccessoryControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RestTemplate template;

    @MockBean
    AddVehicleAccessoryService service;

    @Autowired
    private ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;

    private String token;
    private String userId;
    Document payload = new Document();
    Document payload1 = new Document();
    Document payload2 = null;

    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException {
        token = "token";
        userId = "id";

        String url = String.format("%s/?jwt=%s", authVerifyUrl, token);

        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "customer");
        claims.put("exp", "324323");
        claims.put("userId", userId);
        claims.put("iat", "234234234");
        claims.put("email", "email");

        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

        payload.append("demo", 4);
        String dealerId = "id";
        Mockito.doNothing().when(service).add(payload, dealerId);
        payload1.append("key", "value");
        Mockito.doThrow(new ServiceException()).when(service).add(payload1, dealerId);
    }

    @Test
    void shouldCheckAddVehicleAccessoryControllerWithoutPayloadAndHeader() {
        String url = "/api/vehicles/add-accessory";
        try {
            mockMvc.perform(post(url)).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckAddVehicleAccessoryControllerWithPayload() {
        String url = "/api/vehicles/add-accessory";
        try {
            mockMvc.perform(post(url).header("Authorization", "JWT " + token).contentType(APPLICATION_JSON_UTF8)
                    .content(om.writeValueAsString(payload))).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckAddVehicleAccessoryControllerInternalServerError() {
        String url = "/api/vehicles/add-accessory";
        try {
            mockMvc.perform(post(url).header("Authorization", "JWT " + token).contentType(APPLICATION_JSON_UTF8)
                    .content(om.writeValueAsString(payload1))).andExpect(status().isInternalServerError());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckAddVehicleAccessoryControllerWithoutPayload() {
        String url = "/api/vehicles/add-accessory";
        try {
            mockMvc.perform(post(url).header("Authorization", "JWT " + token).contentType(APPLICATION_JSON_UTF8)
                    .content(om.writeValueAsString(payload2))).andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldCheckAddVehicleAccessoryControllerWithoutClaims() throws RestClientException, JsonProcessingException {
        String url = "/api/vehicles/add-accessory";
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(null));
        try {
            mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(om.writeValueAsString(payload)))
                    .andExpect(status().isUnauthorized());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
