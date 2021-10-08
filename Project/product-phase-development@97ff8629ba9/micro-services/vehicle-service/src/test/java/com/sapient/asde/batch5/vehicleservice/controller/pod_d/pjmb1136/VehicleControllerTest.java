package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1136;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1136.VehicleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Aravind M Krishnan aravind.m.krishnan@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    VehicleService service;

    @BeforeEach
    void setup() throws ServiceException {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Honda");
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle);
        Iterable<Vehicle> v= vehicles;
        Mockito.when(service.getSponsoredVehicleByIdList()).thenReturn(v);
    }

    @Test
    void testGetSponseredVehicleList() throws Exception {
        String url = "/api/vehicles/sponsored";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
