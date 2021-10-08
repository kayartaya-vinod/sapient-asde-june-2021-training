package com.sapient.asde.batch5.authservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.crypto.Cipher;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UpdatePasswordServiceTest {
    @Autowired
    AuthService service;

    @MockBean
    UserDao dao;

    String validUserEmail = "manvendra@google";
    String invalidUserEmail = "abc@gamil.com";
    String validPassword = "secret";

    User existingUser;
    User newUser;
    User unvarifiedUser;
    User updatedUser;
    String validToken;
    String newToken;
    String invalidToken;
    String unVarifiedUserToken;
    String currentPassword = "secret";
    String newPassword = "YouCantGuess";;

    @BeforeEach
    void setup() {
        String hashedPassword = BCryptUtil.hash(validPassword);
        String newHashedPassword = BCryptUtil.hash(newPassword);
        invalidToken = validToken + "dfasf";
        existingUser = new User("abcd1234", "Manvendra", "Singh", "manvendra@google", hashedPassword, "customer", true);
        existingUser.setEmail(CipherUtil.encrypt(existingUser.getEmail()));
        existingUser.setFirstName(CipherUtil.encrypt(existingUser.getFirstName()));
        newUser = new User("abcd1234", "Rohit", "Sharma", "abc@gmail.com", "password", "customer", false);
        updatedUser = new User("abcd1234", "Manvendra", "Singh", "manvendra@google", newHashedPassword, "customer",
                true);
        updatedUser.setEmail(CipherUtil.encrypt(updatedUser.getEmail()));
        updatedUser.setFirstName(CipherUtil.encrypt(updatedUser.getFirstName()));
        unvarifiedUser = new User("abcd1234", "Rohit", "Sharma", "unvarified@gmail.com", "password", "customer", false);
        unvarifiedUser.setEmail(CipherUtil.encrypt(unvarifiedUser.getEmail()));
        unvarifiedUser.setFirstName(CipherUtil.encrypt(unvarifiedUser.getFirstName()));

        validToken = JwtUtil.createDayExpireToken(existingUser.getId(), existingUser.getFirstName(),
                existingUser.getEmail(), existingUser.getUserType());
        newToken = JwtUtil.createDayExpireToken(newUser.getId(), newUser.getFirstName(), newUser.getEmail(),
                newUser.getUserType());
        unVarifiedUserToken = JwtUtil.createDayExpireToken(unvarifiedUser.getId(), unvarifiedUser.getFirstName(),
                unvarifiedUser.getEmail(), unvarifiedUser.getUserType());

        Mockito.when(dao.findByEmail(CipherUtil.encrypt("manvendra@google"))).thenReturn(existingUser);
        Mockito.when(dao.findByEmail(CipherUtil.encrypt("unvarified@gmail.com"))).thenReturn(unvarifiedUser);
        Mockito.when(dao.save(existingUser)).thenReturn(updatedUser);
    }

    @Test
    void shouldAllowUpdateForValidToken() throws ServiceException {
        List<String> lst = service.updatePassword(validToken, newPassword);

        assertNotNull(lst);

        assertEquals("Manvendra", lst.get(1));
    }

    @Test
    void shouldThrowExceptionForInvalidToken() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.updatePassword(invalidToken, newPassword));

    }

    @Test
    void shouldThrowExceptionForUsingPreviousPassword() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.updatePassword(validToken, currentPassword));
    }

    @Test
    void shouldThrowExceptionForUnverifiedUser() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.updatePassword(unVarifiedUserToken, newPassword));
    }
}
