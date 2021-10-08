package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1681;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleAccessoryMongoRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteVehicleAccessoryServiceTest {

    @Autowired
    DeleteVehicleAccessoryServiceImpl service;

    @MockBean
    VehicleAccessoryMongoRepository repo;

    private String vehicleAccId;
    private String userId;
    private VehicleAccessory vehicleAcc;

    @BeforeEach
    void setup() {
        vehicleAccId = "vehicleAccId";
        userId = "userId";
        vehicleAcc = new VehicleAccessory();
        vehicleAcc.setId(vehicleAccId);
        vehicleAcc.setDealerId(userId);
        Mockito.when(repo.findById(vehicleAccId)).thenReturn(Optional.of(vehicleAcc));
        Mockito.doNothing().when(repo).deleteById(vehicleAccId);
    }

    @Test
    void contextLoads() throws Exception {
        assertNotNull(service);
    }

    @Test
    void shouldReturnNothing_whenVehicleAccDeleted() throws ServiceException {
        assertDoesNotThrow(() -> service.deleteVehicleAccessoryById(vehicleAccId, userId));
    }

    @Test
    void shouldThrowServiceException_whenVehicleAccIdOrUserIdIsEmpty() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById("", userId));
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById(" ", userId));
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById(null, userId));
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById(vehicleAccId, ""));
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById(vehicleAccId, " "));
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById(vehicleAccId, null));
    }

    @Test
    void shouldThrowServiceException_whenVehcileAccIdNotFound() throws ServiceException {
        Mockito.when(repo.findById(vehicleAccId)).thenReturn(Optional.ofNullable(null));
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById(vehicleAccId, userId));
    }

    @Test
    void shouldThrowServiceException_whenDifferentUserId() throws ServiceException {
        vehicleAcc.setDealerId("wrongdealer");
        Mockito.when(repo.findById(userId)).thenReturn(Optional.of(vehicleAcc));
        assertThrows(ServiceException.class, () -> service.deleteVehicleAccessoryById(vehicleAccId, userId));
    }

}
