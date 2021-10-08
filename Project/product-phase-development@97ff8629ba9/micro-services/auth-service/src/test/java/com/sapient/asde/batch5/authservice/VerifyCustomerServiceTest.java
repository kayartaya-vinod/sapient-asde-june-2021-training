package com.sapient.asde.batch5.authservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
 class VerifyCustomerServiceTest{
     
    @Autowired
    AuthService service;

    @MockBean
    UserDao dao;

    User existingUser;
    User unvarifiedUser;
    User varifiedUser;
    String unvarifiedEmail;
    String varifiedEmail;

    @BeforeEach
    void setup() {

        existingUser = new User("abcd1234", "Manvendra","Singh", "manvendra@google", "password", "customer",true);
        existingUser.setEmail(CipherUtil.encrypt(existingUser.getEmail()));
        existingUser.setFirstName(CipherUtil.encrypt(existingUser.getFirstName()));
        unvarifiedUser = new User("abcd1234", "Rohit","Sharma", "rohit@gmail.com", "password", "customer",false);
        unvarifiedUser.setEmail(CipherUtil.encrypt(unvarifiedUser.getEmail()));
        unvarifiedUser.setFirstName(CipherUtil.encrypt(unvarifiedUser.getFirstName()));
        varifiedUser=new User("abcd1234", "Rohit","Sharma", "rohit@gmail.com", "password", "customer",true);
        varifiedUser.setEmail(CipherUtil.encrypt(varifiedUser.getEmail()));
        varifiedUser.setFirstName(CipherUtil.encrypt(varifiedUser.getFirstName()));
        unvarifiedEmail = "rohit@gmail.com";
        varifiedEmail = "manvendra@google";

        Mockito.when(dao.findByEmail(CipherUtil.encrypt(varifiedEmail))).thenReturn(existingUser);
        Mockito.when(dao.findByEmail(CipherUtil.encrypt(unvarifiedEmail))).thenReturn(unvarifiedUser);
        Mockito.when(dao.save(unvarifiedUser)).thenReturn(varifiedUser);
    }

    @Test
    void shouldAllowUpdateForUnvarifiedCustomer() throws ServiceException {
        String name = service.verifyCustomer(unvarifiedEmail);
        assertNotNull(name);
        assertEquals("Rohit",name);
    }
    
    @Test
    void shouldThrowExceptionForVarifiedCustomer() throws ServiceException {
        assertThrows(ServiceException.class,() -> service.verifyCustomer(varifiedEmail));
    }
}
