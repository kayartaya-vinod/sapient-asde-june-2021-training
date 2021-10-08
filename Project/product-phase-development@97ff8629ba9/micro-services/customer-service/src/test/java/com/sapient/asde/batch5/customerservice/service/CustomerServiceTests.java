package com.sapient.asde.batch5.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.CipherUtil;
import com.sapient.asde.batch5.customerservice.Utils;
import com.sapient.asde.batch5.customerservice.entity.Address;
import com.sapient.asde.batch5.customerservice.entity.AddressFields;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.entity.User;
import com.sapient.asde.batch5.customerservice.repository.CustomerRepository;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;

import org.apache.kafka.common.KafkaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class CustomerServiceTests {

  @MockBean
  KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  CustomerService service;

  @MockBean
  CustomerRepository customerRepo;

  @MockBean
  UserRepository userRepo;

  @MockBean
  RestTemplate template;

  @Autowired
  ObjectMapper om;

  @Value("${authVerifyUrl}")
  String authVerifyUrl;

  @Value("${authUpdatePasswordUrl}")
  String authUpdatePasswordUrl;

  @Value("${authLoginUrl}")
  String authLoginUrl;

  @Value("${authCreateUrl}")
  String authCreateUrl;

  @Value("${emailSendEmail}")
  String emailSendEmail;

  @Value("${webUrl}")
  String webUrl;

  String token;
  String errorToken;
  String password;

  User u1;

  Customer c3;

  Customer c1;

  @BeforeEach
  void setup() throws RestClientException, JsonProcessingException {

    token = "token";
    errorToken = "errorToken";
    password = "password";
    String url = String.format("%s/?jwt=%s", authVerifyUrl, token);
    Map<String, Object> claims = new HashMap<>();

    claims = Utils.getClaims();

    c3 = new Customer();
    c3 = Utils.getCustomerObject(true);

    Mockito.when(customerRepo.save(c3)).thenReturn(c3);

    Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims));

    Map<String, Object> respBody = new HashMap<>();
    respBody.put("name", "Name");
    respBody.put("token", "Token");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "JWT " + token);
    headers.setContentType(MediaType.APPLICATION_JSON);
    Map<String, Object> reqBody = new HashMap<>();
    reqBody.put("password", password);

    HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(reqBody), headers);

    Mockito.when(template.postForEntity(authUpdatePasswordUrl, entity, String.class))
        .thenReturn(ResponseEntity.ok(om.writeValueAsString(respBody)));

    Map<String, Object> errorRespBody = new HashMap<>();
    respBody.put("message", "Error");

    HttpHeaders errorHeaders = new HttpHeaders();
    errorHeaders.set("Authorization", "JWT " + token);

    Map<String, Object> errorReqBody = new HashMap<>();
    reqBody.put("password", password);

    HttpEntity<String> entity2 = new HttpEntity<>(om.writeValueAsString(errorReqBody), headers);

    Mockito.when(template.postForEntity(authUpdatePasswordUrl, entity2, String.class))
        .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(om.writeValueAsString(errorRespBody)));

    AddressFields af1 = new AddressFields("building1", "street1", "landMark1", "city1", "state1", "pinCode1");
    AddressFields af2 = new AddressFields("building2", "street2", "landMark2", "city2", "state2", "pinCode2");
    Address address = new Address(af1, af2);

    List<String> wishlist1 = new ArrayList<>();
    wishlist1.add("wish1");
    wishlist1.add("wish2");

    c1 = new Customer("id", "id", "alternateEmail", address, "contact", "alternateContact", wishlist1);
    CipherUtil.encryptCustomer(c1);
    Mockito.when(customerRepo.findByUserId("id")).thenReturn(c1);
  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Test
  void shouldUpdateTheCustomer() throws ServiceException {
    Customer actual = service.updateCustomerById(c3);
    Customer expected = c3;
    assertEquals(expected, actual);
  }

  /**
   * @author Abhishek Singh abhishek.singh8@publicissapient.com
   */
  @Test
  void resetPassword() throws ServiceException {
    assertDoesNotThrow(() -> service.resetPassword(token, password));
  }

  /**
   * @author Abhishek Singh abhishek.singh8@publicissapient.com
   */
  @Test
  void resetPasswordShouldThrowServiceException() {
    assertThrows(ServiceException.class, () -> service.resetPassword(null, ""));
    assertThrows(ServiceException.class, () -> service.resetPassword("", ""));
    assertThrows(ServiceException.class, () -> service.resetPassword(errorToken, password));
  }

  /**
   * @author Pritam Patel pritam.patel@publicissapient.com
   */
  @Test
  void getCustomerByUserId() throws ServiceException {
    assertEquals(c1, service.getCustomerByUserId("id"));
  }

  /**
   * @author Pritam Patel pritam.patel@publicissapient.com
   */
  @Test
  void getCustomerByUserIdShouldThrowException() {
    assertThrows(ServiceException.class, () -> service.getCustomerByUserId(" "));
    assertThrows(ServiceException.class, () -> service.getCustomerByUserId(""));
    assertThrows(ServiceException.class, () -> service.getCustomerByUserId(null));
  }

  /**
   * @author Aditya Gheewala aditya.gheewala@publicissapient.com
   */
  @Test
  void resetPasswordLinkShouldNotThrowException() throws ServiceException, JsonProcessingException {

    // mocking get token
    String email = "234nj23nk4@gmail.com";
    String authUrl = authCreateUrl + "?email=" + email;
    Map<String, Object> map = new HashMap<>();
    map.put("token", "token");
    map.put("name", "name");
    Mockito.when(template.getForEntity(authUrl, String.class))
        .thenReturn(new ResponseEntity<String>(om.writeValueAsString(map), HttpStatus.OK));

    // mocking send email
    Map<String, Object> mail = new HashMap<>();
    Map<String, Object> mailContent = new HashMap<>();
    List<String> list = new ArrayList<>();
    list.add(email);
    mailContent.put("to", list);
    // mailContent.put("from", "");
    mailContent.put("subject", "Reset Password");
    String link = "Please follow this link to reset password\n" + webUrl + map.get("token");
    String message = "Hello " + map.get("name") + "\nYou have requested to reset your password"
        + "Please follow the link below to change your password " + link + "\nThanks ";
    mailContent.put("message", message);
    mail.put("mail", mailContent);

    // Map<String, Object> response = new HashMap<>();
    // response.put("success", true);
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);
    // HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(mail),
    // headers);
    // Mockito.when(template.postForEntity(emailSendEmail, entity, String.class))
    // .thenReturn(ResponseEntity.status(HttpStatus.OK).body(om.writeValueAsString(response)));

    SettableListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>();
    Mockito.when(kafkaTemplate.send(anyString(), anyString())).thenReturn(future);

    assertDoesNotThrow(() -> service.resetPasswordLink(email));
  }

  /**
   * @author Aditya Gheewala aditya.gheewala@publicissapient.com
   */
  @Test
  void resetPasswordLinkShouldThrowException() throws ServiceException, JsonProcessingException {
    String email = "234nj23nk4@gmail.com";
    assertThrows(ServiceException.class, () -> service.resetPasswordLink(email));

    String email2 = "asedgrths@gmail.com";
    Map<String, Object> mail = new HashMap<>();
    Map<String, Object> mailContent = new HashMap<>();
    List<String> list = new ArrayList<>();
    list.add(email2);
    mail.put("mail", mailContent);

    Mockito.when(kafkaTemplate.send(anyString(), anyString())).thenThrow(KafkaException.class);

    // Map<String, Object> errorReqBody = new HashMap<>();
    // errorReqBody.put("success", "false");
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);
    // HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(mail),
    // headers);
    // Mockito.when(template.postForEntity(emailSendEmail, entity, String.class))
    // .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(om.writeValueAsString(errorReqBody)));

    assertThrows(ServiceException.class, () -> service.resetPasswordLink(email2));

  }

  /**
   * @author Neha neha1@publicissapient.com
   */
  @Test
  void changePassword() throws ServiceException, RestClientException, JsonProcessingException {
    Map<String, String> payload = new HashMap<>();

    payload.put("email", "email");
    payload.put("password", "oldPassword");

    Map<String, Object> respBody = new HashMap<>();
    respBody.put("token", token);
    respBody.put("name", "Name");

    User u1 = new User("id", "", "", "email", "", "");
    u1.setEmail(CipherUtil.encrypt(u1.getEmail()));
    Mockito.when(userRepo.findById("id")).thenReturn(Optional.of(u1));

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(payload), headers);


    Mockito.when(template.postForEntity(authLoginUrl, entity, String.class))
        .thenReturn(ResponseEntity.ok(om.writeValueAsString(respBody)));

    assertDoesNotThrow(() -> service.changePassword("id", password, "oldPassword", password));

  }

  /**
   * @author Neha neha1@publicissapient.com
   */
  @Test
  void changePasswordShouldThrowServiceException() throws RestClientException, JsonProcessingException {
    assertThrows(ServiceException.class, () -> service.changePassword("", "", "", ""));
    Map<String, String> payload = new HashMap<>();

    payload.put("email", "email");
    payload.put("password", "oldPassword");

    Map<String, Object> respBody = new HashMap<>();
    respBody.put("token", token);
    respBody.put("name", "Name");

    Mockito.when(template.postForEntity(authLoginUrl, om.writeValueAsString(payload), String.class))
        .thenReturn(ResponseEntity.ok(om.writeValueAsString(respBody)));

    assertThrows(ServiceException.class, () -> service.changePassword("id", password, "oldPassword", password));

    User u1 = new User("id", "", "", "email", "", "");

    Mockito.when(userRepo.findById("id")).thenReturn(Optional.of(u1));

    respBody.put("token", errorToken);
    Mockito.when(template.postForEntity(authLoginUrl, om.writeValueAsString(payload), String.class))
        .thenReturn(ResponseEntity.ok(om.writeValueAsString(respBody)));
  }

  @Test
  void shouldInsertNewCustomerByUserId() {
    assertDoesNotThrow(() -> service.addCustomer("userId"));
  }
}
