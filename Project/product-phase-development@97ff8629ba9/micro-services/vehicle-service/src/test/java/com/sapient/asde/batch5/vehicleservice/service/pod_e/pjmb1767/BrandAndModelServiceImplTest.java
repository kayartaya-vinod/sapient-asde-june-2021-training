package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1767;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sapient.asde.batch5.vehicleservice.entity.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BrandAndModelServiceImplTest {
    @Autowired
    BrandAndModelService service;

    @MockBean
    VehicleRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
    }

    @Test
    void returnBrands() throws Exception{
        List<String> brands = new ArrayList<>();
        brands.add("Audi");
        brands.add("BMW");
        List<Select> expected = new ArrayList<>();
        expected.add(new Select("Audi","Audi"));
        expected.add(new Select("BMW","BMW"));
        Mockito.when(repo.groupByBrand()).thenReturn(brands);
        List<Select> actual = service.getBrand();
        assertEquals(expected,actual);
    }

    @Test
    void returnModel() throws Exception{
        Vehicle temp1 = new Vehicle();
        Vehicle temp2 = new Vehicle();
        Vehicle temp3 = new Vehicle();
        List<Vehicle> models = new ArrayList<>();
        temp1.setModel("c100");
        temp1.setPrice(102.2);
        temp1.setId("1234");
        temp1.setBrand("Audi");
        models.add(temp1);
        temp2.setModel("b100");
        temp2.setPrice(1302.2);
        temp2.setId("4567");
        temp2.setBrand("Audi");
        models.add(temp2);
        temp3.setModel("b100");
        temp3.setPrice(1302.2);
        temp3.setId("4568");
        temp3.setBrand("Audi");
        models.add(temp3);
        Pageable pageable = PageRequest.of(0, 100);
        Mockito.when(repo.findByBrand("Audi",pageable)).thenReturn(models);
        List<Select> actual = service.getBrandAndModel("Audi", "",1);
        List<Select> expected = new ArrayList<>();
        expected.add(new Select("1234", "c100  Rs102.2"));
        expected.add(new Select("4567","b100  Rs1302.2"));
        assertEquals(expected,actual);
    }
}
