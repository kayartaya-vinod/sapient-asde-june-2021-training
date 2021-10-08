package com.sapient.asde.batch5.authservice;
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
 class CreateTokenServiceTest {
    @Autowired
    AuthService service;

    @MockBean
    UserDao dao;

    String validUserEmail ="manvendra@google";
    String invalidUserEmail ="abc@gamil.com";

    User existingUser;
    User newUser;

    @BeforeEach
    void setup() {
        existingUser = new User("abcd1234", "Manvendra","Singh", "manvendra@google", "password", "customer",true);
        existingUser.setEmail(CipherUtil.encrypt(existingUser.getEmail()));
        existingUser.setFirstName(CipherUtil.encrypt(existingUser.getFirstName()));
        Mockito.when(dao.findByEmail(CipherUtil.encrypt(validUserEmail))).thenReturn(existingUser);
        Mockito.when(dao.findByEmail(invalidUserEmail)).thenReturn(null);
      //  Mockito.when(dao.findByEmail(invalidUserEmail)).thenReturn(null);
       // Mockito.when(BCryptUtil.verifyHash(invalidUserEmail, null)).thenReturn(false);

    }

    @Test
    void shouldCreateTokenForValidEmail() throws ServiceException {
        List<String> lst = service.createToken(validUserEmail);
        assertNotNull(lst);
    }

    @Test
    void shouldThrowExceptionForUnregisteredEmail() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.createToken(invalidUserEmail));
      
    }
}
