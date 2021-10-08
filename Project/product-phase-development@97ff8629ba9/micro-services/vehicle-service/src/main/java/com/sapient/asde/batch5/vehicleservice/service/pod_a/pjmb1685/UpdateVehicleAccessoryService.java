
/**
 * @author Pritam  Patel  pritam.patel@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1685;


import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;

public interface UpdateVehicleAccessoryService {

   public Document update(Document payload, String dealerId) throws ServiceException;
 
    
}
