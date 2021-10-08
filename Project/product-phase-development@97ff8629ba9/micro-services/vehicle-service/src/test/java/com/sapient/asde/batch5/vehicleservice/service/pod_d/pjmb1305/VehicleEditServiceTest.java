package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1305;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.Optional;

/**
 * @author Suraj kumar suraj.kumar3@publicissapient.com
 */

@SpringBootTest
@AutoConfigureMockMvc
class VehicleEditServiceTest {
   @Autowired
   VehicleEditServiceImpl service;

   @MockBean
   VehicleRepository repo;

   private String vehicleId;
   private String invalidVehicleId;
   private String userId;
   private String someOtherUserId;
   private Vehicle vehicle;
   private Vehicle updatedVehicle;
   private Vehicle vehicleWithoutVehicleId;


   @BeforeEach
   void setup(){
      vehicleId="vehicleId";
      userId="userId";
      invalidVehicleId="invalidVehicleId";
      someOtherUserId="someOtherUserId";
      vehicle = new Vehicle();
      vehicle.setId(vehicleId);
      vehicle.setDealerId(userId);
      updatedVehicle=new Vehicle();
      vehicleWithoutVehicleId=new Vehicle();
      vehicleWithoutVehicleId.setDealerId(userId);
      Mockito.when(repo.findById(vehicleId)).thenReturn(Optional.of(vehicle));
      Mockito.when(repo.findById(invalidVehicleId)).thenReturn(Optional.empty());
      Mockito.when(repo.save(vehicle)).thenReturn(updatedVehicle);
      Mockito.when(repo.findById(someOtherUserId)).thenReturn(Optional.of(vehicle));
   }

   @Test
   void contextLoads() throws Exception{
      assertNotNull(service);
   }

   @Test
   void shouldReturnVehicle_whenVehicleFindById() throws ServiceException{
      assertDoesNotThrow(()-> service.getVehicle(vehicleId,userId));
   }
   
   @Test
   void shouldReturnUpdatedVehicle_whenVehicleUpdated() throws ServiceException{
      assertDoesNotThrow(()->service.saveVehicle(vehicle, userId));
   }
   
   @Test
   void shouldThrowServiceException_whenVehicleIdOrUserIdIsEmpty() throws ServiceException{
      assertThrows(ServiceException.class, () -> service.getVehicle("", userId));
      assertThrows(ServiceException.class, () -> service.getVehicle(" ", userId));
      assertThrows(ServiceException.class, () -> service.getVehicle(null, userId));
      assertThrows(ServiceException.class, () -> service.getVehicle(vehicleId, ""));
      assertThrows(ServiceException.class, () -> service.getVehicle(vehicleId, " "));
      assertThrows(ServiceException.class, () -> service.getVehicle(vehicleId, null));
   }
   @Test
   void shouldThrowServiceException_whenInvalidVehicleIdIsPassed() throws ServiceException{
      assertThrows(ServiceException.class,() -> service.getVehicle(invalidVehicleId, userId));
   }
   
   @Test
   void shouldThrowServiceException_whenVehicleObjectIsNull() throws ServiceException {
      assertThrows(ServiceException.class, () -> service.saveVehicle(null, userId));

   }
   
   @Test
   void shouldThrowServiceException_whenUserIdIsEmptyOrNull() throws ServiceException {
      assertThrows(ServiceException.class,() -> service.saveVehicle(vehicle," "));
      assertThrows(ServiceException.class,()-> service.saveVehicle(vehicle, null));
   }

   @Test
   void shouldThrowServiceException_whenDealerIdNotMatchesUserId() throws ServiceException{
      assertThrows(ServiceException.class,()->service.getVehicle(vehicleId,someOtherUserId));
   }

   @Test
   void shouldThrowServiceException_whenVehicleDoesNotHaveVehicleId() throws ServiceException{
      assertThrows(ServiceException.class,()->service.saveVehicle(vehicleWithoutVehicleId, userId));
   }
   
   @Test
   void shouldThrowServiceException_whenVehicleHaveEmptyVehicleId() throws ServiceException{
      vehicle.setId("");
      assertThrows(ServiceException.class,()->service.saveVehicle(vehicle, userId));
   }
   @Test
   void shouldThrowServiceException_whenNoVehicleFound() throws ServiceException{
      vehicle.setId(invalidVehicleId);
      assertThrows(ServiceException.class,()->service.saveVehicle(vehicle,userId));
   }
   @Test
   void shouldThrowServiceException_whenDealerIdNotMatchesUserIdInVehicleUpdation() throws ServiceException{
      vehicle.setDealerId(someOtherUserId);
      assertThrows(ServiceException.class,()->service.saveVehicle(vehicle, userId));
   }


    
}
