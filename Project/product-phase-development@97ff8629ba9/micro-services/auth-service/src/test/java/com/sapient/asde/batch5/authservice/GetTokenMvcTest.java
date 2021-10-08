package com.sapient.asde.batch5.authservice;

import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class GetTokenMvcTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuthService service;
    User existingUser;
    String validToken;
    List<String> auth;
    String validEmail;
    String invalidEmail;
    @BeforeEach
    void setup() throws ServiceException {
        validEmail="manvendra@google";
        invalidEmail="abc@invalid.com";
        existingUser = new User("abcd1234", "Manvendra", "Singh", "manvendra@google", "password", "customer",true);
        validToken = JwtUtil.createDayExpireToken(existingUser.getId(), existingUser.getFirstName(),
                existingUser.getEmail(), existingUser.getUserType());
        auth = Arrays.asList(validToken, existingUser.getFirstName(),existingUser.getId(),existingUser.getEmail());
        Mockito.when(service.createToken("manvendra@google")).thenReturn(auth);
        Mockito.when(service.createToken(invalidEmail)).thenThrow(new ServiceException());
    }

    @Test
    void shouldGetTokenForValidEmail() throws Exception {
        String url = "/api/auth/get-token?email=" + validEmail;
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());

    }

    @Test
    void shouldGiveBadRequestForInvalidToken() throws Exception {
        String url = "/api/auth/get-token?email=" + invalidEmail;
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isBadRequest()).andReturn();

    }
}