package com.sapient.asde.batch5.vehicleservice.controller.pod_c.pjmb1145;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb_1145.RatingService;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc

class VehicleRatingControllerTest {
  private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RestTemplate template;

  @Autowired
  private ObjectMapper om;

  @MockBean
  private RatingService service;

  @Value("${authVerifyUrl}")
  String authVerifyUrl;

  private String token;
  private String errorToken;
  private String userId;
  private String errorId;

  @BeforeEach
  void setup() throws ServiceException, RestClientException, JsonProcessingException {
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

    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

    url = String.format("%s/?jwt=%s", authVerifyUrl, errorToken);
    claims.put("userId", errorId);
    Feedback feedback = new Feedback();
    feedback.setRating(5);
    feedback.setUserId("user1");
    feedback.setVehicleId("car1");

    Mockito.when(template.getForObject(url,
    String.class)).thenReturn(om.writeValueAsString(null));
    Mockito.when(service.getRating("car1", "user1")).thenReturn(5);
    Mockito.when(service.addRating("car1", "user1", 5)).thenReturn(feedback);
  }

  @Test
  void shouldTestOkForPOSTRating() throws ServiceException {
    String url = "/api/vehicles/rating/";
    Feedback anObject = new Feedback();
    anObject.setRating(5);
    anObject.setVehicleId("car1");
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
      ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
      String requestJson = ow.writeValueAsString(anObject);
      mockMvc
          .perform(
              post(url).header("Authorization", "JWT " + token).contentType(APPLICATION_JSON_UTF8).content(requestJson))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldTestOkForGETRating() throws ServiceException {
    String url = "/api/vehicles/rating/car1";
    try {
      mockMvc.perform(MockMvcRequestBuilders.get(url).header("Authorization", "JWT " + token))
          .andExpect(status().isOk());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldTestUnauthorizedForGETRating() throws ServiceException {
  String url = "/api/vehicles/rating/car1";
  Feedback anObject = new Feedback();
  anObject.setRating(5);
  anObject.setVehicleId("car1");
  ObjectMapper mapper = new ObjectMapper();
  mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
  try {
  mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isUnauthorized());
  } catch (Exception e) {
  e.printStackTrace();
  }
  }

  @Test
  void shouldTestUnauthorizedForPOSTRating() throws ServiceException {
  String url = "/api/vehicles/rating/";
  Feedback anObject = new Feedback();
  anObject.setRating(5);
  anObject.setVehicleId("car1");
  try {
  ObjectMapper mapper = new ObjectMapper();
  mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
  ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
  String requestJson = ow.writeValueAsString(anObject);
  mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestJson))
  .andExpect(status().isUnauthorized());
  } catch (Exception e) {
  e.printStackTrace();
  }
  }
}
