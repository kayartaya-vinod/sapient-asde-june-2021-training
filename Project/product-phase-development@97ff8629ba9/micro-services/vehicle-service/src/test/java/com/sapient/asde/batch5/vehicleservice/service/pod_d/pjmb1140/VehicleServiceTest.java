package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1140;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * @author Deepthi S deepthi.s@publicissapient.com
 */
@SpringBootTest
class VehicleServiceTest {
    @Autowired
    VehicleService service;

    @MockBean
    VehicleRepository repository;
    
    @BeforeEach
    void setup() throws ServiceException {
        Vehicle v1 = new Vehicle();
        v1.setBrand("Tata");
        v1.setVehicleType("Sedan");
        v1.setPrice(1100000.0);
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(v1);

        Vehicle v2 = new Vehicle();
        v2.setBrand("Hyundai");
        v2.setVehicleType("Hatch back");
        v2.setPrice(3000000.0);
        vehicles.add(v2);
        Page<Vehicle> vehiclesPage = new PageImpl<>(vehicles);
        String[] vehTypes = { "Sedan", "Hatch back" };
        String[] brands = { "Tata", "Hyundai" };
        Double min = 100000.0;
        Double max = 5000000.0;

        Mockito.when(repository.findByAdvSearch(vehTypes, brands, min, max, null, null, null, null,1))
                .thenReturn(vehiclesPage);
    }

    @Test
    void shouldReturnSearchedListOfVehicles() throws ServiceException {
        String[] vehTypes = { "Sedan", "Hatch back" };
        String[] brands = { "Tata", "Hyundai" };
        assertNotNull(service.getByAdvancedSearch(vehTypes, brands, 100000.0, 5000000.0, null, null, null, null,1));
    }
    
}
