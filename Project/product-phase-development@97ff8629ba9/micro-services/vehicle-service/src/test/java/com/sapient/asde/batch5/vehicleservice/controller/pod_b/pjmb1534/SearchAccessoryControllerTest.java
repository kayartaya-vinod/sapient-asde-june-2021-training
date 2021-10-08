/**
 * @author Sakshi Yadav  sakshi.yadav@publicissapient.com
 */


package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1534;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1534.SearchByTextService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SearchAccessoryControllerTest {
      
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SearchByTextService service;

    @BeforeEach
    void setup() throws ServiceException {
        VehicleAccessory accessory = new VehicleAccessory();
        accessory.setName("DOOR EDGE GUARD - BLACK");
        List<VehicleAccessory> accessories = new ArrayList<VehicleAccessory>();
        accessories.add(accessory);
        Mockito.when(service.findAccessoryByText("DOOR EDGE GUARD - BLACK",1)).thenReturn(accessories);
    }
    
    @Test
    void testSearchController() throws Exception
    {
        
            String url = "/api/vehicles/accessories/search?q=DOOR EDGE GUARD - BLACK";
            mockMvc.perform(get(url)).andExpect(status().isOk());
    }
    @Test
    void testSearchControllerfornotext() throws Exception
    {
            String url = "/api/vehicles/accessories/search?q=";
            mockMvc.perform(get(url)).andExpect(status().isNotFound());
    }
    @Test
    void testSearchControllerforaccessorynotfound() throws Exception
    {
        
            String url = "/api/vehicles/accessories/search?q=abc";
            mockMvc.perform(get(url)).andExpect(status().isNotFound());
    }
}
