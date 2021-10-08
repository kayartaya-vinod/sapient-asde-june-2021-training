package com.sapient.asde.batch5.emailservice.service;

import com.sapient.asde.batch5.emailservice.entity.Mail;

public interface SimpleMailService {
    public void sendMailMessage(Mail mail) throws ServiceException;

    public void sendAsyncMailMessage(String payload) throws ServiceException;
}
