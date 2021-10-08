package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1155;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1155.VehicleComparisonService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
 class VehicleComparisonControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  @Qualifier("pjmb1155_vehiclecomparisonserviceimpl")
  private VehicleComparisonService service;

  private static final String baseUrl = "/api/vehicles/comparison";

  @MockBean
  private RestTemplate template;

  @Autowired
  private ObjectMapper om;

  @Value("${authVerifyUrl}")
  private String authVerifyUrl;

  private String token;
  private String errorToken;
  private String userId;
  private String errorId;

  @BeforeEach
  void setup() throws ServiceException, RestClientException, JsonProcessingException {
    token = "token";
    userId = "userId";
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
    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));
  }

  @Test
  void shouldReturnNoContent_whenComparisonDeleted() throws Exception {
    String url = baseUrl + "/id";
    Mockito.doNothing().when(service).deleteComparisonMatrixById("id", userId);
    mockMvc.perform(delete(url).header("Authorization", "JWT " + token)).andExpect(status().isNoContent());
  }

  @Test
  void shouldReturnUnauthorised_whenUserIdIsDifferent() throws Exception {
    String url = baseUrl + "/id";
    Mockito.doThrow(new ServiceException("Unauthorized Access.", HttpStatus.UNAUTHORIZED)).when(service)
        .deleteComparisonMatrixById("id", errorId);
    mockMvc.perform(delete(url).header("Authorization", "JWT " + errorToken)).andExpect(status().isUnauthorized());
  }

  @Test
  void shouldReturnNotFound_whenIdIsNotFound() throws Exception {
    String url = baseUrl + "/id";
    Mockito.doThrow(new ServiceException("Vehicle comparison matrix not found.", HttpStatus.NOT_FOUND)).when(service)
        .deleteComparisonMatrixById("id", userId);
    mockMvc.perform(delete(url).header("Authorization", "JWT " + token)).andExpect(status().isNotFound());
  }

  @Test
  void shouldReturnUnauthorised_WhenCustomerHeadersUnavailable() throws Exception {
    String url = baseUrl + "/id";
    mockMvc.perform(delete(url)).andExpect(status().isUnauthorized());
  }

}
