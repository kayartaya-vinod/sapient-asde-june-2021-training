package com.sapient.asde.batch5.emailservice.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.emailservice.CipherUtil;
import com.sapient.asde.batch5.emailservice.entity.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SimpleMailServiceImpl implements SimpleMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${sendEmailUsing}")
    String sendEmailUsing;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    TypeReference<HashMap<String, Object>> typeRef;

    @Override
    public void sendMailMessage(Mail mail) throws ServiceException {
        log.trace("SimpleEmailServiceImpl.sendMailMessage(), mail is {}", mail);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            if (mail == null || mail.getTo() == null || mail.getSubject() == null || mail.getMessage() == null) {
                throw new ServiceException();
            }
            // messageHelper.setFrom(mail.getFrom())
            messageHelper.setFrom(sendEmailUsing);

            String[] mails = mail.getTo().toArray(new String[mail.getTo().size()]);
            for (int i = 0; i < mails.length; i++) {
                mails[i] = CipherUtil.decrypt(mails[i]);
            }

            messageHelper.setTo(mails);
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getMessage());
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

    @Override
    @KafkaListener(topics = "#{'${kafkaEmailTopic}'}", groupId = "report-group")
    public void sendAsyncMailMessage(String payload) throws ServiceException {
        log.trace("SimpleEmailServiceImpl.sendAsyncMailMessage(), payload is {}", payload);

        try {
            Map<String, Object> respBody = mapper.readValue(payload, typeRef);
            Mail mail = mapper.convertValue(respBody.get("mail"), Mail.class);
            this.sendMailMessage(mail);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}
