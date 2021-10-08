package com.sapient.asde.batch5.vehicleservice.repository;

import java.util.List;




import org.bson.json.JsonObject;
import org.springframework.stereotype.Repository;

/**
 * @author Neha neha1@publicissapient.com
 */
@Repository
public interface VehicleAccessoryRepository  {
    public List<JsonObject> vehicleAccessory(String id);

    
    
        
}
