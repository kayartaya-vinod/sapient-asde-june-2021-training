package com.sapient.asde.batch5.authservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.authservice.BCryptUtil;
import com.sapient.asde.batch5.authservice.JwtUtil;
import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.repository.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import main.java.com.sapient.asde.batch5.authservice.CipherUtil;

@Slf4j
@Service
public class AuthService {
    @Autowired
    UserDao dao;
  
    @Autowired
    ObjectMapper om;
    @Autowired
    KafkaTemplate<String, String> template;
    @Value("${kafkaEmailTopic}")
    String kafkaEmailTopic;

    public List<String> login(String email, String password) throws ServiceException{

        try {

            User user = dao.findByEmail(CipherUtil.encrypt(email));
            if (user == null) {
                throw new ServiceException("Please register");
            }
            if (!user.getIsVerified()) {
                throw new ServiceException("User is not verified");
            }
            if (BCryptUtil.verifyHash(password, user.getPassword())) {
                return Arrays.asList(JwtUtil.createDayExpireToken(user.getId(), user.getFirstName(), user.getEmail(),
                        user.getUserType()), CipherUtil.decrypt(user.getFirstName()), user.getId(), CipherUtil.decrypt(user.getEmail()),user.getUserType());
            } else {
                throw new ServiceException("Email or password incorrect");
            }

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<String> register(User user) throws ServiceException {
        if (user == null || user.getEmail() == null || user.getUserType() == null || user.getPassword() == null
                || user.getFirstName() == null) {
            throw new ServiceException("Enter all the fields");
        }

            user.setEmail(CipherUtil.encrypt(user.getEmail()));
            user.setFirstName(CipherUtil.encrypt(user.getFirstName()));
            user.setLastName(CipherUtil.encrypt(user.getLastName()));
       
        User userInDb = dao.findByEmail(user.getEmail());
        try {
            if (userInDb != null) {
                throw new ServiceException("User already exists");
            }
            user.setIsVerified(false);
            String hashedPassword = BCryptUtil.hash(user.getPassword());
            user.setPassword(hashedPassword);
            User newUser = dao.save(user);
            log.info("user is {}", newUser);
            String mail = getMailDetails(user);
            template.send(kafkaEmailTopic, mail); // encryption
            return Arrays.asList(JwtUtil.createDayExpireToken(newUser.getId(), user.getFirstName(), user.getEmail(),
                    user.getUserType()), CipherUtil.decrypt( newUser.getFirstName()), newUser.getId(), CipherUtil.decrypt(newUser.getEmail()));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<String> createToken(String email)  throws ServiceException {

        User user = dao.findByEmail(CipherUtil.encrypt(email));
        if (user == null) {
            throw new ServiceException("User does not  exists");
        } else {
            return Arrays.asList(
                    JwtUtil.createToken(user.getId(), user.getFirstName(), user.getEmail(), user.getUserType()),
                    CipherUtil.decrypt(user.getFirstName()), user.getId(), CipherUtil.decrypt(user.getEmail()));
        }
    }

    public String verifyCustomer(String email) throws ServiceException {

        User userInDb = dao.findByEmail(CipherUtil.encrypt(email));
        log.info("user in verify customer is {}",userInDb);
        if(userInDb.getIsVerified()){
            log.info("User is verfied");
            throw new ServiceException("User already verified");
        }

        try {
            userInDb.setIsVerified(true);
            User newUser = dao.save(userInDb);
            log.info("user is {}", newUser);

            return CipherUtil.decrypt(newUser.getFirstName());

        } catch (Exception e) {
            log.info("exception e is {}", e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
    public List<String> updatePassword(String token, String newPassword) throws ServiceException {

        String email;
        try {
            email = (String) JwtUtil.verify2(token).get("email");
        } catch (Exception e) {
            throw new ServiceException("Token invalid or expired");
        }

        User userInDb = dao.findByEmail(CipherUtil.encrypt(email));
        log.info("userInDb is {}", userInDb);
        if (!userInDb.getIsVerified()) {
            throw new ServiceException("Please verify the email first then try to change the password");
        }
        try {
            if (BCryptUtil.verifyHash(newPassword, userInDb.getPassword())) {
                throw new ServiceException("Please use a different password which you didn't use before");
            }
            String hashedPassword = BCryptUtil.hash(newPassword);
            userInDb.setPassword(hashedPassword);
            User newUser = dao.save(userInDb);
            log.info("newUser is {}", newUser);
            return Arrays.asList(JwtUtil.createDayExpireToken(newUser.getId(), newUser.getFirstName(),
                    newUser.getEmail(), newUser.getUserType()), CipherUtil.decrypt(newUser.getFirstName()), newUser.getId(),
                    CipherUtil.decrypt(newUser.getEmail()));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public String getMailDetails(User user) throws ServiceException {

            String token = JwtUtil.createDayExpireToken(user.getId(), user.getFirstName(),
            user.getEmail(), user.getUserType());
            String link = String.format("http://mycarsolutions.net/verify-account/%s", token);
                String message = "Hello " + CipherUtil.decrypt(user.getFirstName()) + "\nThank You for choosing MyCarSolutions"
                        + " \nYou Are one step away from accessing your account \n"
                        + "Please follow the link below to verify your Mail " + link + "\nThanks ";
                        Map<String, Object> mail = new HashMap<>();
                Map<String, Object> mailContent = new HashMap<>();
                List<String> list = new ArrayList<>();
                list.add(user.getEmail());
                mailContent.put("to", list);
                log.info("mail list is {}", list);
                mailContent.put("subject", "Verification mail");
                mailContent.put("message",message);
                mail.put("mail", mailContent);
                 try {
                    return om.writeValueAsString(mail);
                } catch (Exception e) {
                    throw new ServiceException();
                }
        }
       

       

    

}
