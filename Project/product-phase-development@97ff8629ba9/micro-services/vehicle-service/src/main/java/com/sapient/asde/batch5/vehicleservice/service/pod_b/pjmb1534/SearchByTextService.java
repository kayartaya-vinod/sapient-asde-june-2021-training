/**
 * @author Sakshi Yadav  sakshi.yadav@publicissapient.com
 */


package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1534;

import java.util.List;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;


public interface SearchByTextService {
    
    public List<VehicleAccessory> findAccessoryByText(String text, Integer page) throws ServiceException;

}





