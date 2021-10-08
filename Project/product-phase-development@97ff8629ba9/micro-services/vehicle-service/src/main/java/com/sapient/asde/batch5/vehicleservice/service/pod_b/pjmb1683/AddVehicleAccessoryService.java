/**
 * @author Rohit Bhatt rohit.bhatt1@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1683;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;

public interface AddVehicleAccessoryService {

    public void add(Document payload, String dealerId) throws ServiceException;
}
