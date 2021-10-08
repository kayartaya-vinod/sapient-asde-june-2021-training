package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1136;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.SponsoredVehicles;
import com.sapient.asde.batch5.vehicleservice.repository.SponsoredVehiclesRepository;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Aravind M Krishnan aravind.m.krishnan@publicissapient.com
 */

@SpringBootTest
class VehicleServiceTest {
 
    
    @Autowired
    VehicleService service;

    @MockBean
    VehicleRepository vrepo;

    @MockBean
    SponsoredVehiclesRepository srepo;

    @BeforeEach
    void setup() throws ServiceException {

        SponsoredVehicles spi = new SponsoredVehicles();
        spi.setVid("324214");
        List<SponsoredVehicles> spis = new ArrayList<SponsoredVehicles>();
        spis.add(spi);

        Mockito.when(srepo.findAll()).thenReturn(spis);
    }

    @Test
    void shouldReturnListOfSponsorIDs() throws ServiceException {

       assertNotNull(service.getSponsoredVehicleByIdList());
    }


}
