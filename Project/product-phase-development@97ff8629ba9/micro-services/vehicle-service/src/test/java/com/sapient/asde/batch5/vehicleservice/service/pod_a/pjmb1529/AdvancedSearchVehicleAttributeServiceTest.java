package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1529;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;

import java.util.Arrays;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;
import org.bson.json.JsonObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = {
    AdvancedSearchVehicleAttributeServiceImpl.class })
public class AdvancedSearchVehicleAttributeServiceTest {

  @MockBean
  MongoTemplate mongoTemplate;

  @Autowired
  @Qualifier("pjmb1529_advancedsearchvehicleattributeserviceimpl")
  AdvancedSearchVehicleAttributeSerice service;

  @Test
  void contextLoads() throws Exception {
    assertNotNull(service);
  }

  @Test
  void shouldReturnList_whenTheFunctionIsCalled() throws ServiceException {
    JsonObject jb = new JsonObject("{\"attribute\": \"brand\"}");
    Document doc = new Document();
    doc.append("results", jb);
    AggregationResults ag = new AggregationResults(Arrays.asList(jb), doc);
    Mockito.when(mongoTemplate.aggregate(isA(Aggregation.class), isA(String.class), any())).thenReturn(ag);

    assertNotNull(service.getAdvancedSearchAttributes());
    assertEquals(ag.getMappedResults(), service.getAdvancedSearchAttributes());
  }

}
