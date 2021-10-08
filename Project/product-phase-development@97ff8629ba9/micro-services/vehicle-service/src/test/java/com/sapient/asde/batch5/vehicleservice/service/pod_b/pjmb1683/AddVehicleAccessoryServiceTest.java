/**
 * @author Rohit Bhatt rohit.bhatt1@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1683;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootTest
class AddVehicleAccessoryServiceTest {

    @Mock
    MongoClient mockClient;

    @Mock
    MongoCollection<Document> mockCollection;

    @Mock
    MongoDatabase mockDB;

    @Mock
    InsertOneResult mockResult;

    @Autowired
    AddVehicleAccessoryService service;

    @Autowired
    MongoTemplate mongoTemplate;

    Document payload = new Document();

    @BeforeEach
    void setup() {
        payload.append("demo", 4);
    }

    @Test
    void test() throws ServiceException {
        Query qry = new Query();
        qry.addCriteria(Criteria.where("dealerId").is("dealerId"));
        long c1 = mongoTemplate.count(qry, "vehicle_accessories");
        service.add(payload, "dealerId");
        long c2 = mongoTemplate.count(qry, "vehicle_accessories");
        assertEquals(c1 + 1, c2);
    }

}
