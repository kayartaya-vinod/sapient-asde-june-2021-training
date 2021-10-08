/**
 * @author Rohit Bhatt rohit.bhatt1@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1683;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddVehicleAccessoryServiceImpl implements AddVehicleAccessoryService {

    @Autowired
    MongoTemplate template;

    @CacheEvict(value = "AccessorySearch")
    @Override
    public void add(Document payload, String dealerId) throws ServiceException{

        payload.append("dealerId", dealerId);
        template.insert(payload, "vehicle_accessories");
    }

}
