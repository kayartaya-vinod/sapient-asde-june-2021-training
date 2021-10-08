package com.sapient.asde.batch5.customerservice.service;


import com.sapient.asde.batch5.customerservice.CipherUtil;
import com.sapient.asde.batch5.customerservice.entity.User;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repo;
    @Override
    public String getByUserId(String id) throws ServiceException {
        User user = repo.findById(id).get();
       return  (CipherUtil.decrypt(user.getFirstName()) + " " + CipherUtil.decrypt(user.getLastName()));
    }
}
