
/**
 * @author Pritam  Patel  pritam.patel@publicissapient.com
 * 
 */

package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1685;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1612.GetVehicleAccessoryService;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1685.UpdateVehicleAccessoryService;

import org.bson.Document;
import org.bson.json.JsonObject;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;




@SpringBootTest
@AutoConfigureMockMvc
class UpdateVehicleAccessoryControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    
    @MockBean
    UpdateVehicleAccessoryService service;

    @MockBean
    GetVehicleAccessoryService service2;

    @MockBean
    RestTemplate template;

    @Autowired
    private ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
  
    private String token;
    private String userId;
    private Document record = new Document();
    private Document errorRecord = new Document();
    private List<JsonObject> result = new ArrayList<>();
  
    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException {
        token = "token";
        userId = "user1";
        record.append("id", "value1");
        record.append("demo", "value2");
        errorRecord.append("error", "value");
        String url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        JsonObject doc = new JsonObject("{message:hello}");
        result.add(doc);

        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "customer");
        claims.put("exp", "324323");
        claims.put("userId", userId);
        claims.put("iat", "234234234");
        claims.put("email", "email");
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
        Mockito.when(service.update(errorRecord, userId)).thenThrow(new ServiceException("error"));
        Mockito.when(service.update(record, userId)).thenReturn(record);
        Mockito.when(service2.getVehicleAccessory("value1")).thenReturn(result);
    }
   
@Test
void shouldReturnUnauthorized() throws Exception{
    String url = "/api/vehicles/accessory";    
    mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
    .content(record.toJson())).andExpect((status().isUnauthorized()));
}
@Test
void shouldthrowException() throws Exception{
    String url = "/api/vehicles/accessory";  
    mockMvc.perform(put(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
    .content(errorRecord.toJson())).andExpect((status().isInternalServerError()));
}
@Test
void shouldUpdateAccessory() throws Exception{
    String url = "/api/vehicles/accessory";  
    MvcResult resp =  mockMvc.perform(put(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
    .content(record.toJson())).andExpect((status().isOk())).andReturn();
    assertEquals("{\"data\":[{\"json\":\"{message:hello}\"}],\"success\":true}", resp.getResponse().getContentAsString());
}
}
