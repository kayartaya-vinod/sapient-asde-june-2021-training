package com.sapient.asde.batch5.authservice;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.http.MediaType;
@SpringBootTest
@AutoConfigureMockMvc
class LoginMvcTest {

    @Autowired
    MockMvc mockMvc; 
    @MockBean
    AuthService service;

    User newUser;
    User anotherNewUser;
    User existingUser;
    List<String> auth;
    @BeforeEach
    void setup() throws ServiceException {
        newUser = new User("abcd1234", "Rohit","Sharma", "abc@gmail.com", "password", "customer",false);
        existingUser = new User("abcd1234", "Manvendra","Singh", "manvendra@google", "password", "customer",true);
        
        auth = Arrays.asList(JwtUtil.createDayExpireToken(existingUser.getId(), existingUser.getFirstName(),
                existingUser.getEmail(), existingUser.getUserType()), existingUser.getFirstName(),existingUser.getId(),existingUser.getEmail(),existingUser.getUserType());
        Mockito.when(service.login(existingUser.getEmail(), existingUser.getPassword())).thenReturn(auth);
        Mockito.when(service.login(newUser.getEmail(), newUser.getPassword())).thenThrow(new ServiceException());

    }

    @Test
    void shouldLoginValidUser() throws Exception {
        String url = "/api/auth/login";
        mockMvc.perform(post(url).content(asJsonString(existingUser)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldGiveUnauthorizedStatusForInvalidUserLogin() throws Exception {
        String url = "/api/auth/login";
        mockMvc.perform(post(url).content(asJsonString(newUser)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized()).andReturn();
    }

    public static String asJsonString( Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}