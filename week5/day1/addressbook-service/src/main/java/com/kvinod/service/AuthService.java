package com.kvinod.service;

import com.kvinod.JwtUtil;
import com.kvinod.dao.UserDao;
import com.kvinod.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {

    @Autowired
    UserDao dao;

    public String login(String email, String password) throws ServiceException {
        try {
            User user = dao.findByEmailAndPassword(email, password);
            log.info("user is {}", user);
            return JwtUtil.createToken(user.getId());
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
