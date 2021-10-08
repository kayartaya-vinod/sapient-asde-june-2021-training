
/**
 * @author Pritam Patel pritam.patel@publicissapient.com
 */
package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1307;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DealerVehicleServiceTest {
    @Autowired
    DealerVehicleService service;

    @MockBean
    VehicleRepository repo;

    private String userId;

    Vehicle vehicle1;

    
    


    @BeforeEach
    void setup(){
        userId = "userId";
        String vid = "vid";

        
        vehicle1 = new Vehicle();
        vehicle1.setDealerId(userId);
        vehicle1.setId(vid);

        Mockito.when(repo.save(vehicle1)).thenReturn(vehicle1);
        
    }

    @Test
    void addNewVehicleTest() throws ServiceException{
        Vehicle  actual  = service.addNewVehicle(vehicle1, userId);
        Vehicle expected = vehicle1;
        assertEquals(expected, actual);
    }

    @Test
    void throwsServiceException_whenIdOrUserIdIsemptyTest() throws ServiceException{
        assertThrows(ServiceException.class, () -> service.addNewVehicle(vehicle1, ""));
        assertThrows(ServiceException.class, () -> service.addNewVehicle(vehicle1, " "));
        assertThrows(ServiceException.class, () -> service.addNewVehicle(vehicle1,null));
    }


}
