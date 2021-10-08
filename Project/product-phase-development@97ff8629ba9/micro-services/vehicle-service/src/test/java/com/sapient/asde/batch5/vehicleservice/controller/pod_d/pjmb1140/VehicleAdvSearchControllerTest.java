package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1140;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1140.VehicleService;

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

@SpringBootTest
@AutoConfigureMockMvc
class VehicleAdvSearchControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    VehicleService service;
    
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
        Page<Vehicle> vehiclesPage= new PageImpl<>(vehicles);

        String[] vehTypes={"Sedan","Hatch back"};
        String[] brands = { "Tata", "Hyundai" };
        Double min = 100000.0;
        Double max = 5000000.0;
        
        Mockito.when(service.getByAdvancedSearch(vehTypes, brands,min,max,null,null,null,null,1)).thenReturn(vehiclesPage);
    }

    @Test
    void testgetByAdvancedSearch() throws Exception {
        String url = "/api/vehicles/advanced-search?min_price=100000&max_price=500000&page=1";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
