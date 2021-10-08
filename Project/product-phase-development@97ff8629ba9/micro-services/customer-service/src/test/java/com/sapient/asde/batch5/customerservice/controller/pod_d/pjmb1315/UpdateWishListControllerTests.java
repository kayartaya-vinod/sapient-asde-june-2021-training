package com.sapient.asde.batch5.customerservice.controller.pod_d.pjmb1315;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.customerservice.Utils;
import com.sapient.asde.batch5.customerservice.service.ServiceException;
import com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1315.UpdateWishListService;

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

import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Rajgaria, abhishek.rajgaria@publicissapient.com
 */


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class UpdateWishListControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UpdateWishListService updateService;

    @MockBean
    RestTemplate template;

    @Autowired
    ObjectMapper om;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;
    String url;
    String token;
    String userId;
    Map<String, Object> claims1;
    Map<String, Object> claims2;
    
    Map<String, Object> customerWithId;
    Map<String, Object> customerWithoutId;

    List<String> wishList;
    List<String> updatedWishListByAdd;
    List<String> updatedWishListByRemove;
    


    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException{
        token = "token";
        userId = "userId";
        claims1 = Utils.getClaims();
        claims2 = Utils.getClaims();
        claims2.put("userType", "dealer");
        customerWithId = Utils.getCustomer(true);
        customerWithoutId = Utils.getCustomer(false);
        url = String.format("%s/?jwt=%s",authVerifyUrl,token);
        log.info("Authurl is {}", authVerifyUrl);


        wishList = new ArrayList<String>();
        wishList.add("vehicle1");
        wishList.add("vehilce2");
        updatedWishListByAdd = new ArrayList<String>();
        updatedWishListByAdd.add("vehicle1");
        updatedWishListByAdd.add("vehicle2");
        updatedWishListByAdd.add("vehicle3");
        updatedWishListByRemove = new ArrayList<String>();
        updatedWishListByRemove.add("vehicle1");

        
        

        
    }

    @Test
    void shouldReturnSuccessMessage_whenWishListUpdate() throws Exception{
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));
        String url = "/api/customers/favorites/vehicleId";
        mockMvc.perform(put(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updatedWishListByAdd))).andExpect(status().isOk());
    }

    @Test
    void shouldReturnUnAuthorizedException_whenClaimsNull() throws Exception{
        String url = "/api/customers/favorites/vehicleId";
        mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updatedWishListByAdd))).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturnSuccessMessage_whenUserNotCustomer() throws Exception{
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        String url = "/api/customers/favorites/vehicleId";
        mockMvc.perform(put(url).header("Authorization", "jwt token").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(updatedWishListByAdd))).andExpect(status().isUnauthorized());
    }

    
}
