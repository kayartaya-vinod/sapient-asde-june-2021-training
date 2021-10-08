package com.sapient.asde.batch5.customerservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.entity.Address;
import com.sapient.asde.batch5.customerservice.entity.AddressFields;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.entity.User;
import com.sapient.asde.batch5.customerservice.repository.CustomerRepository;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sapient.asde.batch5.customerservice.CipherUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository cutomerRepo;

  @Autowired
  UserRepository userRepo;

  @Value("${authVerifyUrl}")
  String authVerifyUrl;

  @Value("${authLoginUrl}")
  String authLoginUrl;

  @Value("${authUpdatePasswordUrl}")
  String authUpdatePasswordUrl;

  @Value("${authCreateUrl}")
  String authCreateUrl;

  @Value("${emailSendEmail}")
  String emailSendEmail;

  @Value("${kafkaEmailTopic}")
  String kafkaEmailTopic;
  
  @Value("${webUrl}")
  String webUrl;

  @Autowired
  CustomerRepository customerRepo;

  @Autowired
  ObjectMapper om;

  @Autowired
  RestTemplate template;

  @Autowired
  TypeReference<HashMap<String, Object>> typeRef;

  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;

  private static final String MESSAGE = "message";
  private static final String TOKEN = "token";
  private static final String PASSWORD = "password";

  /**
   * @author Aditya Gheewala aditya.gheewala@publicissapient.com
   */
  @Override
  public void resetPasswordLink(String email) throws ServiceException {

    // getting token from auth service
    String authUrl = authCreateUrl + "?email=" + email;
    ResponseEntity<String> response = template.getForEntity(authUrl, String.class);

    Map<String, Object> map;
    try {
      map = om.readValue(response.getBody(), typeRef);
    } catch (Exception e) {
      throw new ServiceException(e);
    }
    if (response.getStatusCode() != HttpStatus.OK) {
      throw new ServiceException(om.convertValue(map.get("error"), String.class));
    }

    // sending mail request to email service
    Map<String, Object> mail = new HashMap<>();
    Map<String, Object> mailContent = new HashMap<>();
    List<String> list = new ArrayList<>();
    list.add(CipherUtil.encrypt(email));
    mailContent.put("to", list);
    mailContent.put("subject", "Reset Password");
    String link = "Please follow this link to reset password\n" + webUrl + map.get(TOKEN);
    String message = "Hello " + map.get("name") + "\nYou have requested to reset your password"
                        + "Please follow the link below to change your password " + link + "\nThanks ";
    mailContent.put(MESSAGE,
        message);
    mail.put("mail", mailContent);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    try {
      kafkaTemplate.send(kafkaEmailTopic, om.writeValueAsString(mail));
    } catch (Exception e) {
      throw new ServiceException(e);
    }

  }

  /**
   * @author Abhishek Singh abhishek.singh8@publicissapient.com
   */
  @Override
  public void resetPassword(String token, String password) throws ServiceException {
    if (token == null || token.isEmpty()) {
      throw new ServiceException("Token not found.");
    }

    if (password == null || password.isEmpty()) {
      throw new ServiceException("Password not found.");
    }

    try {

      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "JWT " + token);
      headers.setContentType(MediaType.APPLICATION_JSON);

      Map<String, Object> reqBody = new HashMap<>();
      reqBody.put(PASSWORD, password);

      HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(reqBody), headers);

      ResponseEntity<String> response = template.postForEntity(authUpdatePasswordUrl, entity, String.class);
      Map<String, Object> respBody = om.readValue(response.getBody(), typeRef);

      if (response.getStatusCode() != HttpStatus.OK) {
        throw new ServiceException(om.convertValue(respBody.get(MESSAGE), String.class));
      }

    } catch (Exception e) {
      throw new ServiceException(e.getMessage());
    }

  }

  /**
   * @author Neha neha1@publicissapient.com
   */
  @Override
  public String changePassword(String userId, String newPassword, String oldPassword, String confirmPassword)
      throws ServiceException {

    if (newPassword == null || newPassword.isEmpty()) {
      throw new ServiceException("New Password field is empty.");
    }
    if (confirmPassword == null || confirmPassword.isEmpty()) {
      throw new ServiceException("Confirm Password field is empty.");
    }
    if (!newPassword.equals(confirmPassword)) {
      throw new ServiceException("New password and confirm password not matched.");
    }
    try {
      Optional<User> userOptional = userRepo.findById(userId);

      if (!userOptional.isPresent()) {
        throw new ServiceException("Invalid user." + userId);
      }

      User user = userOptional.get();

      Map<String, String> payload = new HashMap<>();

      payload.put("email", CipherUtil.decrypt(user.getEmail()));
      payload.put(PASSWORD, oldPassword);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(payload), headers);

      ResponseEntity<String> response = template.postForEntity(authLoginUrl, entity, String.class);
      Map<String, Object> respBody = om.readValue(response.getBody(), typeRef);

      String token = om.convertValue(respBody.get(TOKEN), String.class);

      if (response.getStatusCode() != HttpStatus.OK) {
        throw new ServiceException(om.convertValue(respBody.get("error"), String.class));
      }

      headers.set("Authorization", "JWT " + token);

      Map<String, Object> reqBody = new HashMap<>();
      reqBody.put(PASSWORD, newPassword);

      entity = new HttpEntity<>(om.writeValueAsString(reqBody), headers);

      response = template.postForEntity(authUpdatePasswordUrl, entity, String.class);
      respBody = om.readValue(response.getBody(), typeRef);

      if (response.getStatusCode() != HttpStatus.OK) {
        throw new ServiceException(om.convertValue(respBody.get(MESSAGE), String.class));
      }

      return respBody.get(TOKEN).toString();

    } catch (Exception e) {
      throw new ServiceException(e.getMessage());
    }

  }

  /**
   * @author Pritam Patel pritam.patel@publicissapient.com
   */
  @Override
  public Customer getCustomerByUserId(String userId) throws ServiceException {

    if (userId == null || userId.trim().isEmpty()) {
      throw new ServiceException("id cannot be null or empty string");
    }

    try {
      Customer customer = customerRepo.findByUserId(userId);
      CipherUtil.decryptCustomer(customer);
      return customer;
      
    } catch (Exception e) {

      throw new ServiceException(e);
    }

  }

  /**
   * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
   */
  @Override
  public Customer updateCustomerById(Customer customer) throws ServiceException {
    CipherUtil.encryptCustomer(customer);
    Customer updatedCustomer=customerRepo.save(customer);
    CipherUtil.decryptCustomer(updatedCustomer);
    return updatedCustomer;
  }

  /**
   * @author Mritunjay Kumar Jay and Jay Aditya Nautiyal
   */
  public void addCustomer(String userId) {
    Customer customer = new Customer();
    log.info("userId is {}",userId);
    log.trace("userId is trace {}",userId);

    customer.setUserId(userId);
    log.info("userId is {}",customer.getUserId());
    log.trace("userId is trace {}",customer.getUserId());

    customer.setAlternateContactNo("");
    customer.setContactNo("");
    List<String> a = new ArrayList<>();
    customer.setWishlist(a);
    AddressFields defaultAdd = new AddressFields("","", "", "", "", "");
    AddressFields anotherAdd = new AddressFields("", "", "", "", "", "");
    Address add = new Address(defaultAdd, anotherAdd);
    customer.setAddress(add);
    customer.setAlternateEmail("");
    CipherUtil.encryptCustomer(customer);
    log.info("userId is {}",customer.getUserId());
    log.trace("userId is trace {}",customer.getUserId());
    customerRepo.save(customer);
    }
}
