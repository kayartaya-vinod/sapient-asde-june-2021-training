package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1273;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.SearchRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1220.SearchService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SearchServiceTest {
    @Autowired
    SearchService service;

    @MockBean
    SearchRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
        Vehicle v1 = new Vehicle();
        v1.setBrand("bmw");
        v1.setVehicleType("Sedan");
        v1.setPrice(1100000.0);
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(v1);

        Vehicle v2 = new Vehicle();
        v2.setBrand("Hyundai");
        v2.setVehicleType("Hatch back");
        v2.setPrice(3000000.0);
        vehicles.add(v2);

      

        Mockito.when(repo.searchByText("bmw 3000000.0",1))
                .thenReturn(vehicles);
    }

    @Test
    void shouldReturnSearchedListOfVehicles() throws ServiceException {
       
        assertNotNull(service.findVehicleByText("bmw 3000000.0",1));
    }
}
