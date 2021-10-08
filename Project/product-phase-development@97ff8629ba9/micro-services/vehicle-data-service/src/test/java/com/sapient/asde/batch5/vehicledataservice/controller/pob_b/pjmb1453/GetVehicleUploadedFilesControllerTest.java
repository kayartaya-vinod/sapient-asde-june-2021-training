/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/
package com.sapient.asde.batch5.vehicledataservice.controller.pob_b.pjmb1453;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;
import com.sapient.asde.batch5.vehicledataservice.service.pod_b.pjmb1453.GetVehicleUploadedFilesService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
class GetVehicleUploadedFilesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private RestTemplate template;

    @MockBean
    GetVehicleUploadedFilesService service;

    @Value("${authVerifyUrl}")
    String authVerifyUrl;

    String url;

    private String token;
    private String userId;
    private String invalidUserId;
    String fileName = "sample.csv";
    List<VehicleUpload> data;
    VehicleUpload V;
    Map<String, Object> claims1 = new HashMap<>();
    Map<String, Object> claims2 = new HashMap<>();

    @BeforeEach
    void setup() throws ServiceException, RestClientException, JsonProcessingException, IOException {

        token = "token";
        userId = "user1";
        invalidUserId = "invaliduser";

        claims1.put("firstName", "Name");
        claims1.put("userType", "dealer");
        claims1.put("exp", "324323");
        claims1.put("userId", userId);
        claims1.put("iat", "234234234");
        claims1.put("email", "email");

        claims2.put("firstName", "Name");
        claims2.put("userType", "customer");
        claims2.put("exp", "324323");
        claims2.put("userId", userId);
        claims2.put("iat", "234234234");
        claims2.put("email", "email");

        url = String.format("%s/?jwt=%s", authVerifyUrl, token);
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));

        data = new ArrayList<VehicleUpload>();
        V = new VehicleUpload();
        V.setId("123");
        V.setDateAndTime("dateAndTime");
        V.setDealerId(userId);
        V.setFileName("fileName");
        V.setNoOfVehicles(30);
        V.setSuccessRatio(98.0);
        data.add(V);

        claims1.put("userType", "dealer");
        Mockito.when(service.getVehicleUploadedFiles(userId)).thenReturn(data);
        Mockito.when(service.getVehicleUploadedFiles(invalidUserId)).thenThrow(new ServiceException());
    }

    @Test
    void shouldGiveStatusOkWithCorrectToken() throws Exception {
        String url = "/api/data/my-uploads/user1";
        mockMvc.perform(get(url).header("Authorization", "JWT " + token)).andExpect(status().isOk());
    }

    @Test
    void shouldGiveUnauthorizedStatusWithoutTokenInAuthorizationHeader() throws Exception {
        String url = "/api/data/my-uploads/user1";
        mockMvc.perform(get(url)).andExpect(status().isUnauthorized());
    }

    @Test
    void shouldGiveBadRequestStatusWithCustomerType() throws Exception {
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims2));
        String url = "/api/data/my-uploads/user1";
        mockMvc.perform(get(url).header("Authorization", "JWT " + token)).andExpect(status().isBadRequest());
    }

    @Test
    void shouldGiveBadRequestStatusWithClaimsNotMatchWithDealerId() throws Exception {
        claims1.put("userId", "user123");
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));
        String url = "/api/data/my-uploads/user1";
        mockMvc.perform(get(url).header("Authorization", "JWT " + token)).andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowExceptionWhenDealerIdNotFound() throws Exception {
        claims1.put("userId", invalidUserId);
        Mockito.when(template.getForObject(url, String.class)).thenReturn(om.writeValueAsString(claims1));
        String url = "/api/data/my-uploads/invaliduser";
        mockMvc.perform(get(url).header("Authorization", "JWT " + token)).andExpect(status().isBadRequest());
    }



}
