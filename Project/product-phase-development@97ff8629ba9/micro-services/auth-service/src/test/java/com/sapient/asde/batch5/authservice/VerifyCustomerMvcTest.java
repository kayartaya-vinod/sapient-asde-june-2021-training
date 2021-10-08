package com.sapient.asde.batch5.authservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import main.java.com.sapient.asde.batch5.authservice.CipherUtil;

@SpringBootTest
@AutoConfigureMockMvc
class VerifyCustomerMvcTest {

    @Autowired
    ObjectMapper om;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuthService service;

    User newUser;
    User existingUser;
    String auth;
    String jwt;
    String invalidJwt;
    String token;
    String email;

    @BeforeEach
    void setup() throws ServiceException {
        newUser = new User("abcd1234", "Rohit", "Sharma", "abc@gmail.com", "password", "customer", false);
        newUser.setEmail(CipherUtil.encrypt(newUser.getEmail()));
        newUser.setFirstName(CipherUtil.encrypt(newUser.getFirstName())) ;
        existingUser = new User("abcd1234", "Manvendra", "Singh", "manvendra@google", "password", "customer", true);
        existingUser.setEmail(CipherUtil.encrypt(existingUser.getEmail()));
        existingUser.setFirstName(CipherUtil.encrypt(existingUser.getFirstName()));
        token = JwtUtil.createDayExpireToken(existingUser.getId(), existingUser.getFirstName(), existingUser.getEmail(),
                existingUser.getUserType());
        auth = newUser.getFirstName();
        jwt = "jwt " + token;
        invalidJwt = "jwt" + "invalid";
        Mockito.when(service.verifyCustomer(email)).thenReturn(auth);
        Mockito.when(service.verifyCustomer(email)).thenThrow(new ServiceException());
    }

    @Test
    void shouldUpdateIsVarified() throws Exception {
        String url = "/api/auth/verify-account";
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", email);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).header("Authorization", jwt)
                .content(om.writeValueAsString(payload))).andExpect(status().isOk());
    }

    @Test
    void shouldSendBadRequestStatusOnIncorrectTokenPassword() throws Exception {
        String url = "/api/auth/verify-account";
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", email);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).header("Authorization", invalidJwt)
                .content(om.writeValueAsString(payload))).andExpect(status().isUnauthorized());
    }
}
