package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1767;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1767.BrandAndModelService;
import org.springframework.test.web.servlet.MockMvc;

import com.sapient.asde.batch5.vehicleservice.entity.Select;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

@SpringBootTest
@AutoConfigureMockMvc
class AddingToComparisonMatrixControllerTest {
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    BrandAndModelService service;

    @BeforeEach
    void setup() throws ServiceException {
        
    }

    @Test
    void brandAndModelEmpty() throws Exception{
        List<Select> brands = new ArrayList<>();
        Select temp = new Select();
        temp.setLabel("Audi");
        temp.setValue("Audi");
        brands.add(temp);
        Mockito.when(service.getBrand()).thenReturn(brands);

        String url = "/api/vehicles/comparison?brand=&&model=";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void forBrandReturnModel() throws Exception{
        String url = "/api/vehicles/comparison?brand=BMW&&model=";
        List<Select> models = new ArrayList<>();
        Select temp = new Select();
        temp.setLabel("c20 Rs.1000");
        temp.setValue("1234");
        models.add(temp);
        Mockito.when(service.getBrandAndModel("BMW","",1)).thenReturn(models);

        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
}
