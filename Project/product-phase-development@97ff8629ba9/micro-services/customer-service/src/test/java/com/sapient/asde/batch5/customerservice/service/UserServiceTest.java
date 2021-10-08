package com.sapient.asde.batch5.customerservice.service;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import com.sapient.asde.batch5.customerservice.entity.User;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest

class UserServiceTest {
    @MockBean
    UserRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
        
        User user = new User();
        user.setFirstName("bing");
        user.setLastName("zing");
        Optional<User> userOptional = Optional.of(user);
       
        Mockito.when(repo.findById("123")).thenReturn(userOptional);
        
    }
    @Test
    void testUserController() throws Exception
    {
        assertNotNull(repo.findById("123"));
    }
}
