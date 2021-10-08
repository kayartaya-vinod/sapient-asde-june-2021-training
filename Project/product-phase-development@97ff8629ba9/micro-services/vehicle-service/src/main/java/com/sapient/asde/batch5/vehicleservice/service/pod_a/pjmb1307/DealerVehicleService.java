


/**
 * @author Pritam Patel pritam.patel@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1307;


import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;



public interface DealerVehicleService {

    
    public Vehicle addNewVehicle(Vehicle vehicle,String userId) throws ServiceException;
}

    

