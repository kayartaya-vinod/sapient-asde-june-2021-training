package com.sapient.asde.batch5.customerservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.Utils;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.service.CustomerService;
import com.sapient.asde.batch5.customerservice.service.ServiceException;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTests {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  RestTemplate template;

  @MockBean
  CustomerService service;

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

    Mockito.when(service.updateCustomerById(customerObjectWithId)).thenReturn(customerObjectWithId);
    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

    url = String.format("%s/?jwt=%s", authVerifyUrl, errorToken);
    claims.put("userId", "errorId");
    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

    url = String.format("%s/?jwt=%s", authVerifyUrl, "autherror");
    Mockito.when(template.getForObject(url, String.class)).thenThrow(new RestClientException("Server not running"));

    Mockito.doNothing().when(service).resetPassword(isA(String.class), isA(String.class));
    Mockito.doThrow(ServiceException.class).when(service).resetPassword(isNull(), isNull());

    Mockito.when(service.getCustomerByUserId("id")).thenReturn(new Customer());
    Mockito.when(service.getCustomerByUserId("errorId")).thenThrow(ServiceException.class);
  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Test
  void shouldReturnSuccessMessage_WhenCustomerUpdated() throws Exception {
    String url = "/api/customers";
    mockMvc.perform(put(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
        .content(om.writeValueAsString(customerWithId))).andExpect(status().isOk());
  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Test
  void shouldReturnUnAuthenticatedMessage_WhenCustomerHeadersUnavailable() throws Exception {
    String url = "/api/customers";
    mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(customerWithId)))
        .andExpect(status().isUnauthorized());
  }

  /**
   * @author Abhishek Singh abhishek.singh8@publicissapient.com
   */
  @Test
  void resetPassword() throws Exception {

    String url = "/api/customers/reset-password";
    Map<String, Object> payload = new HashMap<>();
    payload.put("token", token);
    payload.put("password", password);

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(payload)))
        .andExpect(status().isOk());
  }

  /**
   * @author Abhishek Singh abhishek.singh8@publicissapient.com
   */
  @Test
  void resetPasswordShouldReturnNotFound() throws JsonProcessingException, Exception {

    String url = "/api/customers/reset-password";
    Map<String, Object> payload = new HashMap<>();
    payload.put("token2", token);
    payload.put("password2", password);

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(payload)))
        .andExpect(status().isNotFound());
  }

  /**
   * @author Pritam Patel pritam.patel@publicissapient.com
   */
  @Test
  void getCustomerByUserId() throws Exception {

    String url = "/api/customers";

    mockMvc.perform(get(url).header("Authorization", "JWT " + token)).andExpect(status().isOk());
  }

  /**
   * @author Pritam Patel pritam.patel@publicissapient.com
   */
  @Test
  void getCustomerByUserIdShouldThrowException() throws JsonProcessingException, Exception {

    String url = "/api/customers";

    mockMvc.perform(get(url).header("Authorization", "JWT " + errorToken)).andExpect(status().isNotFound());
  }

  /**
   * @author Aditya Gheewala aditya.gheewala@publicissapient.com
   */
  @Test // reset Password Link
  void testResetPasswordLink() throws Exception {
    doNothing().when(service).resetPasswordLink(isA(String.class));
    String email = "234nj23nk4@gmail.com";
    String url = "/api/customers/reset-password-link?email=" + email;
    mockMvc.perform(get(url)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
  }

  /**
   * @author Aditya Gheewala aditya.gheewala@publicissapient.com
   */
  @Test // reset Password Link
  void testResetPasswordLinkException() throws Exception {
    String email = "234nj23nk4@gmail.com";
    Mockito.doThrow(ServiceException.class).when(service).resetPasswordLink(isA(String.class));
    String url = "/api/customers/reset-password-link?email=" + email;
    mockMvc.perform(get(url)).andDo(MockMvcResultHandlers.print()).andExpect(status().isNotFound());
  }

  /**
   * @author Neha neha1@publicissapient.com
   */
  @Test
  void changePassword() throws Exception {
    Mockito.when(service.changePassword(userId, password, oldPassword, confirmPassword)).thenReturn(token);

    String url = "/api/customers/change-password";
    Map<String, Object> payload = new HashMap<>();
    payload.put("password", password);
    payload.put("oldPassword", oldPassword);
    payload.put("confirmPassword", confirmPassword);

    mockMvc.perform(post(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
        .content(om.writeValueAsString(payload))).andExpect(status().isOk());
  }

  /**
   * @author Neha neha1@publicissapient.com
   */
  @Test
  void changePasswordShouldThrowError() throws JsonProcessingException, Exception {

    Mockito.doThrow(ServiceException.class).when(service).changePassword(isA(String.class), isNull(), isNull(),
        isNull());

    String url = "/api/customers/change-password";
    Map<String, Object> payload = new HashMap<>();

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(payload)))
        .andExpect(status().isUnauthorized());

    mockMvc.perform(post(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
        .content(om.writeValueAsString(payload))).andExpect(status().isNotFound());
  }

  /**
   * @author Mutharasan E mutharasan.e@publicissapient.com
   */
  @Test
  void shouldCheckAuthErrorInChangePassword() throws Exception {
    String url = "/api/customers/change-password";
    Map<String, Object> payload = new HashMap<>();
    payload.put("password", password);
    payload.put("oldPassword", oldPassword);
    payload.put("confirmPassword", confirmPassword);

    MvcResult result = mockMvc.perform(post(url).header("Authorization", "jwt autherror")
        .contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(payload)))
        .andExpect(status().isGatewayTimeout()).andReturn();
    assertEquals("Error: Server not running", result.getResponse().getContentAsString());
  }

  @Test
  void shouldCheckAuthErrorInCustomerUpdate() throws Exception {
    String url = "/api/customers";
    MvcResult result = mockMvc.perform(put(url).header("Authorization", "jwt autherror")
        .contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(customerWithId)))
        .andExpect(status().isGatewayTimeout()).andReturn();
    assertEquals("Error: Server not running", result.getResponse().getContentAsString());
  }

  @Test
  void shouldCheckAuthErrorInGetCustomerByUserId() throws Exception {

    String url = "/api/customers";

    MvcResult result = mockMvc.perform(get(url).header("Authorization", "JWT autherror"))
        .andExpect(status().isGatewayTimeout()).andReturn();
    assertEquals("Error: Server not running", result.getResponse().getContentAsString());

  }

  @Test
  void shouldInsertNewCustomerByUserId() throws Exception {
 
    String url = "/api/customers/add-customer/";
    Map<String, Object> payload = new HashMap<>();
    payload.put("userId", userId);

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(payload)))
        .andExpect(status().isOk());
  }
}
