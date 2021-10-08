package com.sapient.asde.batch5.customerservice.controller;

import com.sapient.asde.batch5.customerservice.service.ServiceException;
import com.sapient.asde.batch5.customerservice.service.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService service;

    @BeforeEach
    void setup() throws ServiceException {

        String s = "myname";
        Mockito.when(service.getByUserId("123")).thenReturn(s);
        Mockito.when(service.getByUserId("")).thenThrow(new ServiceException("NO TEXT ENTERED", HttpStatus.NO_CONTENT));

    }

    @Test
    void testUserController() throws Exception {
        String url = "/api/users?q=123";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void testUserControllerForNothing() throws Exception {
        String url = "/api/users?q=";
        mockMvc.perform(get(url)).andExpect(status().isNoContent());
    }
}
