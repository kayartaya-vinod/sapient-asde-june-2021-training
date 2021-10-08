package com.sapient.asde.batch5.authservice;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;

import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.repository.UserDao;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class RegisterServiceTest {
    @Autowired
    AuthService service;

    @MockBean
    UserDao dao;

    @MockBean
    KafkaTemplate<String, String> kafkaTemplate;
    User newUser, existingUser, nullUser, nullNameUser, nullEmailUser, nullTypeUser, nullPasswordUser;

    @BeforeEach
    void setup() {

        newUser = new User("abcd1234", "Rohit", "Bhatt", "abc@gmail.com", "password", "customer", false);
        existingUser = new User("abcd1234", "Manvendra", "Singh", "manvendra@google", "password", "customer", true);
        nullUser = null;
        nullNameUser = new User("id3447gj", null, "Bhatt", "abc@gmail.com", "password", "customer", false);
        nullEmailUser = new User("id172kjgdj", "Sumitesh", "Naithani", null, "password", "customer", false);
        nullPasswordUser = new User("id12222", "Rohit", "Bhatt", "abc@gmail.com", null, "customer", false);
        nullTypeUser = new User("id12jgkcgj", "Sakshi", "Yadav", "abc@gmail.com", "password", null, false);
        Mockito.when(dao.save(newUser)).thenReturn(newUser);
        Mockito.when(dao.findByEmail("email")).thenReturn(null);
        Mockito.when(dao.findByEmail("manvendra@google")).thenReturn(existingUser);
        SettableListenableFuture<SendResult<String, String>> future = new SettableListenableFuture<>();
        Mockito.when(kafkaTemplate.send(anyString(), anyString())).thenReturn(future);
    }

    @Test
    void shouldRegisterUser() throws ServiceException {
        List<String> lst = service.register(newUser);
        assertNotNull(lst);
    }

    @Test
    void shouldNotRegisterExistingUser() throws ServiceException {
        try {
            service.register(existingUser);
            fail("User already exists");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldThrowServiceExceptionForNullUser() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.register(nullUser));
    }

    @Test
    void shouldThrowServiceExceptionForNullNameUser() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.register(nullNameUser));
    }

    @Test
    void shouldThrowServiceExceptionForNullEmailUser() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.register(nullEmailUser));
    }

    @Test
    void shouldThrowServiceExceptionForNullPasswordUser() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.register(nullPasswordUser));
    }

    @Test
    void shouldThrowServiceExceptionForNullTypeUser() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.register(nullTypeUser));
    }
}
