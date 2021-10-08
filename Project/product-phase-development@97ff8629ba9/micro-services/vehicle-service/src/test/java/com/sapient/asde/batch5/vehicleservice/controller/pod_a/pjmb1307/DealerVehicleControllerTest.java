
/**
 * @author Pritam Patel pritam.patel@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1307;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1307.DealerVehicleService;

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

 class DealerVehicleControllerTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
    MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestTemplate template;

   
    @MockBean
    DealerVehicleService  service;

    @Autowired
    ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;

    private String token;
    private String errorToken;
    private String userId;
    private String errorId;

    Map<String, Object> addNewVehicle;

    Vehicle addNewVehicleObject;

    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException{
    token = "token";
    userId = "user1";
    errorId = "errorId ";
    errorToken = "errorToken";

    String url = String.format("%s/?jwt=%s", authVerifyUrl, token);

    
    Map<String, Object> claims = new HashMap<>();
    claims.put("firstName", "Name");
    claims.put("userType", "customer");
    claims.put("exp", "324323");
    claims.put("userId", userId);
    claims.put("iat", "234234234");
    claims.put("email", "email");
    claims.put("userType","dealer");

    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

    url = String.format("%s/?jwt=%s", authVerifyUrl, errorToken);
    claims.put("userId", errorId);
    

    Mockito.when(template.getForObject(url,
    String.class)).thenReturn(om.writeValueAsString(null));


    Vehicle newVehicle = new Vehicle();
    newVehicle.setBrand("BMW");
    newVehicle.setColor("red");

    Mockito.when(service.addNewVehicle(newVehicle, userId)).thenReturn(newVehicle);
    }
@Test
void shouldTestOkForaddNewVehicle() throws ServiceException{
    String url = "/api/vehicles";
    Vehicle newVehicle = new Vehicle();
    newVehicle.setBrand("BMW");
    newVehicle.setColor("red");
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
      ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
      String requestJson = ow.writeValueAsString(newVehicle);
      mockMvc
          .perform(
              post(url).header("Authorization", "JWT " + token).contentType(APPLICATION_JSON_UTF8).content(requestJson))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    }

    @Test
    void shouldTestUnauthorizedForPostVehicle() throws ServiceException {
    String url = "/api/vehicles";
    Vehicle newVehicle = new Vehicle();
    newVehicle.setBrand("BMW");
    newVehicle.setColor("red");
    try {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(newVehicle);
    mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson))
    .andExpect(status().isUnauthorized());
    } catch (Exception e) {
    e.printStackTrace();
    }
    }

}
