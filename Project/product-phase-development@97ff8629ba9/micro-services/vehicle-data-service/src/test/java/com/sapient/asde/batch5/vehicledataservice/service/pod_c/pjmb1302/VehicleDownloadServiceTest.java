package com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1302;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sapient.asde.batch5.vehicledataservice.entity.Dealer;
import com.sapient.asde.batch5.vehicledataservice.entity.VehicleDownload;
import com.sapient.asde.batch5.vehicledataservice.repository.DealerRepository;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleDownloadRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Yogeshwar Chaturvedi yogeshwar.chaturvedi@publicissapient.com
 */
@SpringBootTest
class VehicleDownloadServiceTest {

    @Autowired
    VehicleDownloadService service;

    @MockBean
    VehicleDownloadRepository vehicleRepo;

    @MockBean
    DealerRepository dealerRepo;

    Writer writer;

    @BeforeEach
    void setup() throws ServiceException {
        VehicleDownload vehicle = new VehicleDownload();
        vehicle.setBrand("Maruthi");
        List<VehicleDownload> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        Dealer dealer = new Dealer();
        dealer.setId("id");
        // Optional<VehicleDownload> download = new Optional<VehicleDownload>
        Mockito.when(vehicleRepo.findByDealerId("id")).thenReturn(vehicles);
        Mockito.when(dealerRepo.findByUserId("id")).thenReturn(dealer);
        Mockito.when(dealerRepo.findByUserId("invalid")).thenReturn(null);
        Mockito.when(vehicleRepo.findById("id")).thenReturn(Optional.of(vehicle));
    }

    @Test
    void shouldThrowServiceException_whenIdNotFound() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.getAllVehicles(writer, "invalid"));
    }

    @Test
    void shooulCheckGetAllVehicles() throws ServiceException {
        assertDoesNotThrow(() -> service.getAllVehicles(writer, "id"));
    }

    @Test
    void shooulCheckGetVehiclesById() throws ServiceException {
        List<String> ids = new ArrayList<>();
        ids.add("id");
        assertDoesNotThrow(() -> service.getVehiclesById(ids, writer));
    }

}
