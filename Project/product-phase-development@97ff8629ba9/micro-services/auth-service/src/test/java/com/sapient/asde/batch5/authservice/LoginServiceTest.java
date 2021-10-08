package com.sapient.asde.batch5.authservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.repository.UserDao;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import main.java.com.sapient.asde.batch5.authservice.CipherUtil;
@SpringBootTest
 class LoginServiceTest {
    @Autowired
    AuthService service;

    @MockBean
    UserDao dao;

    String validUserEmail ="manvendra@google";
    String invalidUserEmail ="abc@gamil.com";
    String validPassword ="secret";
    String wrongPassword ="password";
    String unvarifiedEmail="unvarified@gmail.com";

    User existingUser;
    User newUser;
    User unvarifiedUser;

    @BeforeEach
    void setup() {
        String hashedPassword = BCryptUtil.hash(validPassword);
        existingUser = new User("abcd1234", "Manvendra","Singh", "manvendra@google", hashedPassword, "customer",true);
        existingUser.setEmail(CipherUtil.encrypt(existingUser.getEmail()));
        existingUser.setFirstName(CipherUtil.encrypt(existingUser.getFirstName()));
        newUser = new User("abcd1234", "Rohit","Sharma", "abc@gmail.com", "password", "customer",false);
        newUser.setEmail(CipherUtil.encrypt(newUser.getEmail()));
        newUser.setFirstName(CipherUtil.encrypt(newUser.getFirstName()));
        unvarifiedUser = new User("abcd1234", "Rohit","Sharma", "unvarified@gmail.com", "password", "customer",false);
        unvarifiedUser.setEmail(CipherUtil.encrypt(unvarifiedUser.getEmail()));
        unvarifiedUser.setFirstName(CipherUtil.encrypt(unvarifiedUser.getFirstName()));
        Mockito.when(dao.findByEmail(CipherUtil.encrypt("manvendra@google"))).thenReturn(existingUser);
        Mockito.when(dao.findByEmail(CipherUtil.encrypt("unvarified@gmail.com"))).thenReturn(unvarifiedUser);
    }

    @Test
    void shouldAllowLoginForValidUser() throws ServiceException {
        List<String> lst = service.login(validUserEmail, validPassword);
        assertNotNull(lst);
       assertEquals("Manvendra",lst.get(1));
    }

    @Test
    void shouldThrowExceptionForWrongPassword() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.login(validUserEmail, wrongPassword));
      
    }
    @Test
    void shouldThrowExceptionForUnregisteredEmail() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.login(invalidUserEmail, wrongPassword));
    }
    @Test
    void shouldThrowExceptionForUnverified() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.login(unvarifiedEmail, validPassword));
    }
}
