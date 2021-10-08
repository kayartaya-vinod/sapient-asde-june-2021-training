package com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1317;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.Utils;
import com.sapient.asde.batch5.customerservice.entity.Address;
import com.sapient.asde.batch5.customerservice.entity.AddressFields;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.entity.User;
import com.sapient.asde.batch5.customerservice.entity.Vehicle;
import com.sapient.asde.batch5.customerservice.repository.CustomerRepository;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;
import com.sapient.asde.batch5.customerservice.repository.VehicleRepository;
import com.sapient.asde.batch5.customerservice.service.CustomerService;
import com.sapient.asde.batch5.customerservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class FavoriteVehicleServiceTests {

  @MockBean
  KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  CustomerService service;

  @Autowired
  FavoriteVehicleService favService;

  @MockBean
  CustomerRepository customerRepo;

  @MockBean
  UserRepository userRepo;

  @MockBean
  VehicleRepository vr;

  @MockBean
  RestTemplate template;

  @Autowired
  ObjectMapper om;

  @Value("${vehicleServiceUrl}")
  String vehicleUrl;

  @Value("${authVerifyUrl}")
  String authVerifyUrl;

  @Autowired
  TypeReference<HashMap<String, Object>> typeRef;

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

    Vehicle v1= new Vehicle();
    v1.setId("wish1");
    // Vehicle v2= new Vehicle()
    // v1.setId("wish2")
    
    Map<Object,Object> response=new HashMap<Object,Object>();
    
    Map<String, Object> respBody = new HashMap<>();
    respBody.put("data", v1);
    respBody.put("success", "Token");
    response.put("Object",respBody);



    



    AddressFields af1 = new AddressFields("building1", "street1", "landMark1", "city1", "state1", "pinCode1");
    AddressFields af2 = new AddressFields("building2", "street2", "landMark2", "city2", "state2", "pinCode2");
    Address address = new Address(af1, af2);

    List<String> wishlist1 = new ArrayList<>();
    wishlist1.add("wish1");

    c1 = new Customer("id", "id", "alternateEmail", address, "contact", "alternateContact", wishlist1);
    List<Vehicle> lst=new ArrayList<>();
    lst.add(v1);
    
    Mockito.when(customerRepo.findByUserId("id")).thenReturn(c1);
    Mockito.when(customerRepo.findByUserId("invalidId")).thenReturn(null);
    Optional<Vehicle> v2= Optional.of(v1);
    Mockito.when(vr.findById("id")).thenReturn(v2);
    Mockito.when(customerRepo.findByWishlist()).thenReturn(lst);
    Mockito.when(vr.findById("invalidId")).thenReturn(null);
  }
  @Test
  void shouldCallFavoriteVehicleService() throws ServiceException{
    
    assertNotNull(favService.customerWishlist("id"));
  }

  @Test
  void shouldReturnCustomer() throws ServiceException{

    assertDoesNotThrow(()-> customerRepo.findByUserId("id"));
  }

  @Test
  void getCustomerByUserIdShouldThrowException() {
    assertThrows(ServiceException.class, () -> service.getCustomerByUserId(" "));
    assertThrows(ServiceException.class, () -> service.getCustomerByUserId(""));
    assertThrows(ServiceException.class, () -> service.getCustomerByUserId(null));
  }


  @Test
  void shouldReturnFavoriteVehicle() throws ServiceException{
    
    Vehicle v1=new Vehicle();
    v1.setId("wish1");
    List<Vehicle> lst=new ArrayList<>();
    lst.add(v1);
    Optional<Vehicle> v2=Optional.of(v1); 
    assertEquals(v2, vr.findById("id"));
  }

  @Test
  void shouldReturnListOfFavoriteVehicle() throws ServiceException{
    
    Vehicle v1=new Vehicle();
    v1.setId("wish1");
    List<Vehicle> lst=new ArrayList<>();
    lst.add(v1);
    assertEquals(lst, customerRepo.findByWishlist());
  }
 

}
