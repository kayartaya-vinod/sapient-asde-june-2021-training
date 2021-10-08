
/**
 * @author Pritam  Patel  pritam.patel@publicissapient.com 
 */

package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1685;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = {
    UpdateVehicleAccessoryServiceImpl.class })
public class UpdateVehicleAccessoryServiceTest {
    
@MockBean
MongoTemplate mongoTemplate;

@Autowired
UpdateVehicleAccessoryService service;


@Test
void shouldReturnUpdatedDocumnet_whenTheFunctionIsCalled() throws ServiceException{

    Document dc = new Document();
    dc.append("desc", "very good accessory");
    dc.append("name", "wheel");
    String s = "612492c50696e8167a6206d9";
    ObjectId obId = new ObjectId(s);
    dc.append("_id", obId);
    Query qry = new Query();
    qry.addCriteria(Criteria.where("_id").is(obId));
    Mockito.when(mongoTemplate.findAndReplace(qry, dc, "vehicle_accessories")).thenReturn(dc);
    
    
    
    
    Document dc1 = new Document();
    dc1.append("desc", "very good accessory");
    dc1.append("name", "wheel");
    String s1 = "612492c50696e8167a6206d9";
    dc1.append("id", s1);
    
    Document result = service.update(dc1, "1223");
    assertEquals(result,dc);
}


}
