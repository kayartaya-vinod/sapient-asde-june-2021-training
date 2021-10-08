package com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1299;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicledataservice.entity.Dealer;
import com.sapient.asde.batch5.vehicledataservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleUploadRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

/**
 * @author Mutharasan E mutharasan.e@publicissapient.com
 */

 
@SpringBootTest
class VehicleUploadServiceTest {
    @Autowired
    VehicleUploadService service;

  
    @MockBean
    VehicleRepository vehicleRepository;

    @MockBean
    VehicleUploadRepository repo;

    String userId;
    MockMultipartFile sampleFile;

    List<Vehicle> vehicles = new ArrayList<Vehicle>();
    List<Vehicle> except = new ArrayList<Vehicle>();

    VehicleUpload metaData = new VehicleUpload("id", "id", "dateAndTime", 2, 100.);
    File initialFile ;
    InputStream targetStream ;
    @BeforeEach
    void setup() throws ServiceException,IOException {
        
        initialFile = new File("src/test/java/com/sapient/asde/batch5/vehicledataservice/service/pod_c/pjmb1299/vehicles.csv");
        targetStream = new FileInputStream(initialFile);
        userId = "123abc";
        sampleFile = new MockMultipartFile("uploaded-file", "file.csv", "text/csv",
                "This is the file content".getBytes());
        Dealer dealer = new Dealer();
        dealer.setId("id");
        Vehicle vehicle = new Vehicle();
        vehicle.setDealerId("abc1");
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setDealerId("abc12");
        vehicles.add(vehicle);
        except.add(vehicle2);
        Mockito.when(vehicleRepository.saveAll(vehicles)).thenReturn(vehicles);
        Mockito.when(vehicleRepository.saveAll(vehicles)).thenReturn(vehicles);
        Mockito.when(repo.save(metaData)).thenReturn(metaData);

    }

    @Test
    void ifVehiclesareNull() throws IOException, ServiceException {
        Map<Object,Object> map = service.store(sampleFile.getInputStream(), "abc", "file.csv");
        assertEquals(false,map.get("Success"));
    }

    @Test
    void ifVehiclesarestored() throws IOException, ServiceException {
       assertNotNull(vehicleRepository.saveAll(vehicles));
    }

    @Test
    void ifVehiclesMetaDataarestored() throws IOException, ServiceException {
        assertNotNull(repo.save(metaData));
    }

    @Test
    void shouldBeTrue() throws IOException,ServiceException{
        Map<Object,Object> map = service.store(targetStream,"abc1","vehicles.csv");
        assertEquals(true, map.get("Success"));
    }

    
}