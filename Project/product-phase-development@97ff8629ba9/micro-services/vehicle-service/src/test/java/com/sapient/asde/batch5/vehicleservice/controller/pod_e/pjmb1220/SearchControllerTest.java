package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1220;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1220.SearchService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc

class SearchControllerTest {
      
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SearchService service;

    @BeforeEach
    void setup() throws ServiceException {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("bmw");
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle);
        Mockito.when(service.findVehicleByText("bmw",1)).thenReturn(vehicles);
        Mockito.when(service.findVehicleByText("",1)).thenThrow(new ServiceException("NO TEXT ENTERED", HttpStatus.NO_CONTENT));
    }
    
    @Test
    void testSearchController() throws Exception
    {
        
            String url = "/api/vehicles/search?q=bmw";
            mockMvc.perform(get(url)).andExpect(status().isOk());
    }
    @Test
    void testSearchControllerfornotext() throws Exception
    {
            String url = "/api/vehicles/search?q=";
            mockMvc.perform(get(url)).andExpect(status().isNoContent());
    }
}
