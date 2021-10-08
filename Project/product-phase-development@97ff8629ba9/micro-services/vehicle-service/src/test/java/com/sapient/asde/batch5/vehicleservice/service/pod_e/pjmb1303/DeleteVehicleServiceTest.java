package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1303;


import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
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
class DeleteVehicleServiceTest {
    
    @Autowired
    DeleteVehicleServiceImpl service;
  
    @MockBean
    VehicleRepository repo;

    private String vehicleId;
    private String userId;
    private Vehicle vehicle;


    @BeforeEach
    void setup(){
       vehicleId = "vehicleId";
       userId="userId";
       vehicle = new Vehicle();
       vehicle.setId(vehicleId);
       vehicle.setDealerId(userId);
       Mockito.when(repo.findById(vehicleId)).thenReturn(Optional.of(vehicle));
       Mockito.doNothing().when(repo).deleteById(vehicleId);
    }


    @Test
    void contextLoads() throws Exception {
      assertNotNull(service);
    }

    @Test
    void shouldReturnNothing_whenVehicleDeleted() throws ServiceException {
      assertDoesNotThrow(() -> service.deleteVehicleById(vehicleId, userId));
    }

    @Test
    void shouldThrowServiceException_whenVehicleIdOrUserIdIsEmpty() throws ServiceException {
      assertThrows(ServiceException.class, () -> service.deleteVehicleById("", userId));
      assertThrows(ServiceException.class, () -> service.deleteVehicleById(" ", userId));
      assertThrows(ServiceException.class, () -> service.deleteVehicleById(null, userId));
      assertThrows(ServiceException.class, () -> service.deleteVehicleById(vehicleId, ""));
      assertThrows(ServiceException.class, () -> service.deleteVehicleById(vehicleId, " "));
      assertThrows(ServiceException.class, () -> service.deleteVehicleById(vehicleId, null));
    }

    @Test
    void shouldThrowServiceException_whenIdNotFound() throws ServiceException {
      Mockito.when(repo.findById(vehicleId)).thenReturn(Optional.ofNullable(null));
      assertThrows(ServiceException.class, () -> service.deleteVehicleById(vehicleId, userId));
    }

    @Test
    void shouldThrowServiceException_whenUserIdIsDifferent() throws ServiceException {
      vehicle.setDealerId("wrongdealer");
      Mockito.when(repo.findById(userId)).thenReturn(Optional.of(vehicle));
      assertThrows(ServiceException.class, () -> service.deleteVehicleById(vehicleId, userId));
    }
}
