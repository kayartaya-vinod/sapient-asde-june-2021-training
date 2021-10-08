package com.sapient.asde.batch5.vehicleservice.controller.pod_c.pjmb1138;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1138.VehicleListService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Mutharasan E mutharasan.e@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class VehicleListControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    VehicleListService service;

    @BeforeEach
    void setup() throws ServiceException {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Maruthi");
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle);
        Page<Vehicle> vehicleList = new PageImpl<>(vehicles);
        Mockito.when(service.getVehiclewithPagination(1)).thenReturn(vehicleList);
    }

    @Test
    void testgetVehiclewithPagination() throws Exception {
        String url = "/api/vehicles?page=1";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

}
