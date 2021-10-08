package com.sapient.asde.batch5.customerservice.service;

import com.sapient.asde.batch5.customerservice.entity.Customer;

public interface CustomerService {
  public void resetPasswordLink(String email) throws ServiceException;

  public void resetPassword(String token, String password) throws ServiceException;

  public String changePassword(String userId, String newPassword, String oldPassword, String confirmPassword)
      throws ServiceException;

  public Customer getCustomerByUserId(String userId) throws ServiceException;

  public Customer updateCustomerById(Customer customer) throws ServiceException;

  public void addCustomer(String userId) throws ServiceException;
}
