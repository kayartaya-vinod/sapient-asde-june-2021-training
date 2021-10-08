/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/
package com.sapient.asde.batch5.vehicledataservice.service.pod_b.pjmb1453;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleUploadRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class GetVehicleUploadedFilesServiceTest {

    @Autowired
    GetVehicleUploadedFilesService service;

    @MockBean
    VehicleUploadRepository repo;

    List<VehicleUpload> data;
    VehicleUpload V;

    String invalidDealerId;
    String validDealerId;

    @BeforeEach
    void setup() throws ServiceException, IOException {
        invalidDealerId = "dealer123";
        validDealerId = "dealer";

        data = new ArrayList<VehicleUpload>();
        V = new VehicleUpload();
        V.setId("123");
        V.setDateAndTime("dateAndTime");
        V.setDealerId(validDealerId);
        V.setFileName("fileName");
        V.setNoOfVehicles(30);
        V.setSuccessRatio(98.0);
        data.add(V);

        Mockito.when(repo.getFilesByDealerId(validDealerId)).thenReturn(data);
    }

    @Test
    void shouldGetUploadedFiles() throws ServiceException {
        List<VehicleUpload> expected = data;
        List<VehicleUpload> actual = service.getVehicleUploadedFiles(validDealerId);
        assertEquals(expected, actual);
    }
}
