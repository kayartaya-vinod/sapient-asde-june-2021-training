package com.sapient.asde.batch5.authservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import main.java.com.sapient.asde.batch5.authservice.CipherUtil;
@SpringBootTest
@AutoConfigureMockMvc
class VerifyTokenMvcTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AuthService service;
    
    User existingUser;
    String validToken;
    String invalidToken;

    @BeforeEach
    void setup() throws ServiceException {

        existingUser = new User("abcd1234", "Manvendra", "Singh", "manvendra@google", "password", "customer",true);
        existingUser.setEmail(CipherUtil.encrypt(existingUser.getEmail()));
        existingUser.setFirstName(CipherUtil.encrypt(existingUser.getFirstName()));
        validToken = JwtUtil.createDayExpireToken(existingUser.getId(), existingUser.getFirstName(),
                existingUser.getEmail(), existingUser.getUserType());
        invalidToken = "adfshfjjshfkjdhfkjhdkjhfas";

    }

    @Test
    void shouldVarifyToken() throws Exception {
        String url = "/api/auth/verify?jwt=" + validToken;
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());

    }

    @Test
    void shouldGiveUNautherisedStatusForInvalidToken() throws Exception {
        String url = "/api/auth/verify?jwt=" + invalidToken;
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isUnauthorized()).andReturn();

    }
}