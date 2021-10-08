/**
 * @author Sakshi Yadav  sakshi.yadav@publicissapient.com
 */


package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1534;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.repository.SearchAccessoryRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class SearchAccessoryServiceTest {
    @Autowired
    SearchByTextService service;

    @MockBean
    SearchAccessoryRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
        VehicleAccessory v1 = new VehicleAccessory();
        v1.setName("DOOR EDGE GUARD - BLACK");
        v1.setPrice(1493.5);

        List<VehicleAccessory> accessories = new ArrayList<VehicleAccessory>();
        accessories.add(v1);

        VehicleAccessory v2 = new VehicleAccessory();
        v2.setName("ROGER WIPER BLADE");
        v2.setPrice(5646.47);
        accessories.add(v2);

        Mockito.when(repo.searchByText("DOOR EDGE GUARD - BLACK 5646.4", 1)).thenReturn(accessories);
    }

    @Test
    void shouldReturnSearchedListOfVehicleAccesories() throws ServiceException {
        assertNotNull(service.findAccessoryByText("DOOR EDGE GUARD - BLACK 5646.4", 1));
    }
}
