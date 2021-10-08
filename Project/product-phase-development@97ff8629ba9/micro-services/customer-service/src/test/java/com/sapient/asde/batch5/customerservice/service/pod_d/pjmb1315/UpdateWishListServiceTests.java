package com.sapient.asde.batch5.customerservice.service.pod_d.pjmb1315;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.customerservice.Utils;
import com.sapient.asde.batch5.customerservice.entity.Customer;
import com.sapient.asde.batch5.customerservice.repository.CustomerRepository;
import com.sapient.asde.batch5.customerservice.repository.UserRepository;
import com.sapient.asde.batch5.customerservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Abhishek Rajgaria, abhishek.rajgaria@publicissapient.com
 */
   
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class UpdateWishListServiceTests {
    @MockBean
    CustomerRepository customerRepo;

    @MockBean
    UserRepository userRepo;
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UpdateWishListService updateService;

    
    String userId;
    String vehicleId;
    Map<String, Object> customerWithId;
    Map<String, Object> customerWithoutId;

    Customer customerObjectWithId;
    Customer customerObjectWithoutId;

    List<String> wishList;
    List<String> updatedWishListByAdd;
    List<String> updatedWishListByRemove;

    @BeforeEach
    void Setup() throws ServiceException{
        userId = "id";
        vehicleId = "vehicleId";

        customerWithId = Utils.getCustomer(true);
        customerWithoutId = Utils.getCustomer(false);

        customerObjectWithId = Utils.getCustomerObject(true);
        customerObjectWithoutId = Utils.getCustomerObject(false);

        wishList = new ArrayList<String>();
        wishList.add("car1");
        wishList.add("car2");
        updatedWishListByAdd = new ArrayList<String>();
        updatedWishListByAdd.add("car1");
        updatedWishListByAdd.add("car2");
        updatedWishListByAdd.add("car3");
        updatedWishListByRemove = new ArrayList<String>();
        updatedWishListByRemove.add("car1");


        
    }

    @Test
    void shouldThrowServiceException_whenVehicleIdNull() throws ServiceException{
        assertThrows(ServiceException.class,()->updateService.updateWishList(userId, null));
    }

    @Test
    void shouldThrowServiceException_whenVehicleIdEmptyString() throws ServiceException{
        assertThrows(ServiceException.class,()->updateService.updateWishList(userId, ""));
    }

    @Test
    void shouldThrowServiceException_whenUserIdNull() throws ServiceException{
        assertThrows(ServiceException.class,()->updateService.updateWishList(null, vehicleId));
    }

    @Test
    void shouldThrowServiceException_whenUserIdEmptyString() throws ServiceException{
        assertThrows(ServiceException.class,()->updateService.updateWishList("", vehicleId));
    }

    @Test
    void shouldThrowServiceException_whenCustomerNot() throws ServiceException{
        assertThrows(ServiceException.class, ()->updateService.updateWishList(userId, vehicleId));
    }

    @Test
    void shouldAddVehicleId_whenVehicleidNotWishList() throws ServiceException {
        Mockito.when(customerRepo.findByUserId(userId)).thenReturn(customerObjectWithId);
        List<String> ids = updateService.updateWishList("id", "car3");
        assertEquals(updatedWishListByAdd, ids);
    }

    @Test
    void shouldRemoveVehicleId_whenVehicleidNotWishList() throws ServiceException {
        Mockito.when(customerRepo.findByUserId(userId)).thenReturn(customerObjectWithId);
        List<String> ids = updateService.updateWishList("id", "car2");
        assertEquals(updatedWishListByRemove, ids);
    }
}
