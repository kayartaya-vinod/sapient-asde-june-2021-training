package com.sapient.asde.batch5.emailservice;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.emailservice.entity.Mail;
import com.sapient.asde.batch5.emailservice.service.ServiceException;
import com.sapient.asde.batch5.emailservice.service.SimpleMailService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestMvcRequests {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @MockBean
  private SimpleMailService mailService;

  @BeforeEach
  public void before() throws ServiceException {
    doNothing().when(mailService).sendMailMessage(isA(Mail.class));
    doThrow(ServiceException.class).when(mailService).sendMailMessage(isNull());
  }

  @Test
  public void testSendMail() throws JsonProcessingException, Exception {
    String url = "/api/mail";
    Mail mail = new Mail();
    // mail.setFrom("from")
    mail.getTo().add("to");
    mail.setSubject("subject");
    mail.setMessage("message");
    Map<String, Object> payload = new HashMap<>();
    payload.put("mail", mail);

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(payload)))
        .andExpect(status().isOk());
  }

  @Test
  public void sendMailShouldGiveBadRequest() throws JsonProcessingException, Exception {
    String url = "/api/mail";
    Mail mail = new Mail();
    // mail.setFrom("from")
    mail.getTo().add("to");
    mail.setSubject("subject");
    mail.setMessage("message");
    Map<String, Object> payload = new HashMap<>();
    payload.put("send-mail", mail);

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(payload)))
        .andExpect(status().isBadRequest());
  }

}
