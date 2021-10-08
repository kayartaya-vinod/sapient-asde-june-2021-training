package com.sapient.asde.batch5.emailservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;


import com.sapient.asde.batch5.emailservice.CipherUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.emailservice.entity.Mail;
import com.sapient.asde.batch5.emailservice.service.ServiceException;
import com.sapient.asde.batch5.emailservice.service.SimpleMailService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
@EmbeddedKafka
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestServiceLayer {

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  BlockingQueue<ConsumerRecord<String, String>> records;

  KafkaMessageListenerContainer<String, String> container;

  @Autowired
  private SimpleMailService emailServiceImpl;

  @Autowired
  private ObjectMapper om;

  @Value("${kafkaEmailTopic}")
  private String kafkaEmailTopic;

  @MockBean
  private JavaMailSender javaMailSender;

  private MimeMessage mimeMessage;

  @BeforeEach
  public void before() {
    mimeMessage = new MimeMessage((Session) null);
    when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

  }

  @BeforeAll
  public void setup() {

    Map<String, Object> configs = new HashMap<>(KafkaTestUtils.consumerProps("consumer", "false", embeddedKafkaBroker));
    DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(configs,
        new StringDeserializer(), new StringDeserializer());
    ContainerProperties containerProperties = new ContainerProperties(kafkaEmailTopic);
    container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    records = new LinkedBlockingQueue<>();
    container.setupMessageListener((MessageListener<String, String>) records::add);
    container.start();
    ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
  }

  @AfterAll
  void tearDown() {
    container.stop();
  }

  @Test
  public void emailTest() throws ServiceException, MessagingException, IOException {
    Mail mail = new Mail();
    // mail.setFrom("from")
    mail.setMessage("message");
    mail.getTo().add(CipherUtil.encrypt("to"));
    mail.setSubject("subject");
    emailServiceImpl.sendMailMessage(mail);
    assertEquals(mail.getTo().get(0), CipherUtil.encrypt(mimeMessage.getRecipients(RecipientType.TO)[0].toString()));
    // assertEquals(mail.getFrom(), mimeMessage.getFrom()[0].toString())
    assertEquals(mail.getSubject(), mimeMessage.getSubject());
    assertEquals(mail.getMessage(), mimeMessage.getContent());
  }

  @Test
  public void shouldThrowServiceException() {
    Mail mail = new Mail();
    // mail.setFrom("from")
    mail.setMessage("message");
    mail.getTo().add("to");
    mail.setSubject("subject");

    // mail.setFrom("null");
    // assertThrows(ServiceException.class, () ->
    // emailServiceImpl.sendMailMessage(mail));
    // mail.setFrom("from");

    mail.setMessage(null);
    assertThrows(ServiceException.class, () -> emailServiceImpl.sendMailMessage(mail));
    mail.setMessage("message");

    mail.setSubject(null);
    assertThrows(ServiceException.class, () -> emailServiceImpl.sendMailMessage(mail));
    mail.setMessage("subject");

    mail.setTo(null);
    assertThrows(ServiceException.class, () -> emailServiceImpl.sendMailMessage(mail));

    Mail newMail = null;
    assertThrows(ServiceException.class, () -> emailServiceImpl.sendMailMessage(newMail));
  }

  @Test
  void testKafka() throws JsonProcessingException, InterruptedException {
    // Arrange
    Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
    Producer<String, String> producer = new DefaultKafkaProducerFactory<>(configs, new StringSerializer(),
        new StringSerializer()).createProducer();

    Mail mail = new Mail();
    // mail.setFrom("from")
    mail.setMessage("message");
    mail.getTo().add("to");
    mail.setSubject("subject");

    Map<String, Object> payload = new HashMap<>();
    payload.put("mail", mail);

    // Act
    producer.send(new ProducerRecord<>(kafkaEmailTopic, om.writeValueAsString(payload)));
    producer.flush();

    // Assert
    ConsumerRecord<String, String> singleRecord = records.poll(100, TimeUnit.MILLISECONDS);

    // assertThat(singleRecord).isNotNull();
    assertEquals(om.writeValueAsString(payload), singleRecord.value());
  }

  @Test
  void sendAsyncMailMessage() throws ServiceException, MessagingException, IOException {
    Mail mail = new Mail();
    // mail.setFrom("from")
    mail.setMessage("message");
    mail.getTo().add(CipherUtil.encrypt("to"));
    mail.setSubject("subject");

    Map<String, Object> payload = new HashMap<>();
    payload.put("mail", mail);

    emailServiceImpl.sendAsyncMailMessage(om.writeValueAsString(payload));
    assertEquals(mail.getTo().get(0), CipherUtil.encrypt(mimeMessage.getRecipients(RecipientType.TO)[0].toString()));
    // assertEquals(mail.getFrom(), mimeMessage.getFrom()[0].toString())
    assertEquals(mail.getSubject(), mimeMessage.getSubject());
    assertEquals(mail.getMessage(), mimeMessage.getContent());
  }

  @Test
  void sendAsyncMailMessageShouldThrowException() {
    Mail mail = new Mail();
    // mail.setFrom("from")
    mail.getTo().add("to");
    mail.setSubject("subject");

    Map<String, Object> payload = new HashMap<>();
    payload.put("mail", mail);

    assertThrows(ServiceException.class, () -> emailServiceImpl.sendAsyncMailMessage(om.writeValueAsString(payload)));
  }

}
