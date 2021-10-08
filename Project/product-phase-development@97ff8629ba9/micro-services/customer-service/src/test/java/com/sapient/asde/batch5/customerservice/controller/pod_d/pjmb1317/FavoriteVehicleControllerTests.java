package com.sapient.asde.batch5.customerservice.controller.pod_d.pjmb1317;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.Utils;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.entity.Vehicle;
import com.sapient.asde.batch5.customerservice.service.CustomerService;
import com.sapient.asde.batch5.customerservice.service.ServiceException;
import com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1317.FavoriteVehicleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
class FavoriteVehicleServiceTests {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  RestTemplate template;

  @MockBean
  CustomerService service;

  @MockBean
  FavoriteVehicleService favService;

  @Autowired
  ObjectMapper om;

  Customer c1;

  @Value("${authVerifyUrl}")
  String authVerifyUrl;

  String token;
  String errorToken;

  String password;
  String oldPassword;
  String confirmPassword;
  String userId;

  Customer customerObjectWithId;
  Customer customerObjectWithoutId;

  Map<String, Object> customerWithId;
  Map<String, Object> customerWithoutId;

  @BeforeEach
  void setup() throws ServiceException, RestClientException, JsonProcessingException {
    token = "token";
    password = "password";
    userId = "userId";
    oldPassword = "old_password";
    confirmPassword = "confirm_password";
    errorToken = "errorToken";

    customerWithId = new HashMap<>();
    customerWithoutId = new HashMap<>();
    customerWithId = Utils.getCustomer(true);
    customerWithoutId = Utils.getCustomer(false);

    customerObjectWithId = new Customer();
    customerObjectWithId = Utils.getCustomerObject(true);
    customerObjectWithoutId = new Customer();
    customerObjectWithoutId = Utils.getCustomerObject(false);

    Map<String, Object> claims = new HashMap<>();
    claims = Utils.getClaims();

    String url = String.format("%s/?jwt=%s", authVerifyUrl, token);
    List<Vehicle> lst = new ArrayList<>();
    Vehicle v = new Vehicle();
    v.setId("id");
    lst.add(v);

    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

    url = String.format("%s/?jwt=%s", authVerifyUrl, "autherror");
    Mockito.when(template.getForObject(url, String.class)).thenThrow(new RestClientException("Server not running"));

    Mockito.when(favService.customerWishlist("userId")).thenReturn(lst);

  }

  @Test
  void getCustomerWishlist() throws Exception {

    String url = "/api/customers/favorites";
    mockMvc.perform(get(url).header("Authorization", "JWT " + token)).andExpect(status().isOk());
  }

  @Test
  void shouldCheckAuthErrorInGetCustomerWishlist() throws Exception {

    String url = "/api/customers/favorites";
    MvcResult result = mockMvc.perform(get(url).header("Authorization", "JWT autherror"))
        .andExpect(status().isGatewayTimeout()).andReturn();
    assertEquals("Error: Server not running", result.getResponse().getContentAsString());

  }

  @Test
  void getCustomerWishlistShouldThrowException() throws Exception {

    String url = "/api/customers/favorites";
    mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
  }

}