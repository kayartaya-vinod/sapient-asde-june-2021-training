package com.sapient.asde.batch5.vehicledataservice.service.pod_e.pjmb1589;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import com.sapient.asde.batch5.vehicledataservice.repository.VehicleAccessoryRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest

 class VehicleAccesssoriesServiceTest {
    @Autowired
    AccessoriesService service;

    @MockBean
    VehicleAccessoryRepository repo;

   

    String userId;
    MockMultipartFile sampleFile,sampleFile2;

    @BeforeEach
    void setup() throws ServiceException,IOException, ParseException {
        
        
        userId = "123abc";
        sampleFile = new MockMultipartFile("uploaded-file", "file.csv", "text/csv",
                "This is the file content".getBytes());
        sampleFile2=new MockMultipartFile("uploaded-file", "file.csv", "text/csv",
        "This is the file content".getBytes());
        Mockito.when(repo.saveInDb(sampleFile, userId)).thenReturn(true);
        Mockito.when(repo.saveInDb(sampleFile2, userId)).thenReturn(false);
    }
    @Test
    void fileAdded() throws IOException, ServiceException, ParseException {
        assertEquals(true,service.store(sampleFile, userId, "file.json"));
    }
    @Test
    void fileNotAdded() throws IOException, ServiceException, ParseException {
        assertEquals(false,service.store(sampleFile2, userId, "file.json"));
    }
}
