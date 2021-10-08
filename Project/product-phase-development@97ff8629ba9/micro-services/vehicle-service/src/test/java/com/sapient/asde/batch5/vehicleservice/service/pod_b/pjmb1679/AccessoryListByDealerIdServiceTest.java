package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1679;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.repository.AccessoryRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
/**
 * @author Manvendra Singh
 */
@SpringBootTest
class AccessoryListByDealerIdServiceTest {
    @Autowired
    GetAccesssoryListByDealerIdService service;

    @MockBean
    AccessoryRepository repository;

    @BeforeEach
    void setup() throws ServiceException {
        List<VehicleAccessory> fake = new ArrayList<>();
        Page<VehicleAccessory> page = new PageImpl<>(fake);
        Pageable pageable = PageRequest.of(0, 12);
        Mockito.when(repository.findByDealerId("validUserId",pageable))
                .thenReturn(Utils.getAccessoryList());
        Mockito.when(repository.findByDealerId("invalidUserId",pageable)).thenReturn(page);
    }

    @Test
    void shouldReturnVehicleList() throws ServiceException {
        Page<VehicleAccessory> actual = service.getAccessoryListByDealerId("validUserId",1);
        Page<VehicleAccessory> expected = Utils.getAccessoryList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnServiceException_WhenNoMatchingIdFound() {
        assertThrows(ServiceException.class, () -> service.getAccessoryListByDealerId("invalidUserId",1));
    }
}
