
/**
 * @author Pritam  Patel  pritam.patel@publicissapient.com 
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1685;


import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UpdateVehicleAccessoryServiceImpl implements UpdateVehicleAccessoryService{

    @Autowired
    MongoTemplate template;
    
    @Override
    public Document  update(Document payload, String dealerId) throws ServiceException {
       
        log.info("{}" , payload);
        
        log.info("payload id is {}", payload.get("id"));
        log.info("payload is {}", payload);
        ObjectId objectId = new ObjectId(payload.get("id").toString());

        payload.append("_id",objectId);
        payload.remove("id");
        log.info("payload2 is {}", payload);
        Query qry = new Query();
        qry.addCriteria(Criteria.where("_id").is(objectId));

       Document xc = template.findAndReplace(qry, payload, "vehicle_accessories");
        log.info("aaaa {}", xc);
        return xc;
        
    }
    

}











