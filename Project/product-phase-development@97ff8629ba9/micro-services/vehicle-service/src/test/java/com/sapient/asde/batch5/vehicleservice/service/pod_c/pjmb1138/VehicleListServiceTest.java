package com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1138;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Mutharasan E mutharasan.e@publicissapient.com
 */
@SpringBootTest
class VehicleListServiceTest {
    @Autowired
    VehicleListService service;

    @MockBean
    VehicleRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
        Pageable pageable = PageRequest.of(0, 12,Sort.by("_id"));
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Maruthi");
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle);
        Page<Vehicle> vehicleList = new PageImpl<>(vehicles);
        Mockito.when(repo.findAll(pageable)).thenReturn(vehicleList);
    }

    @Test
    void checkgetVehicleListService() throws ServiceException {
        assertNotNull(service.getVehiclewithPagination(1));
    }
}
