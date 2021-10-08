package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1349;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
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

@SpringBootTest
class GetVehicleListByDealerIdServiceImpTest {
    @Autowired
    GetVehicleListByDealerIdServiceImp service;

    @MockBean
    VehicleRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
        List<Vehicle> fake = new ArrayList<>();
        Page<Vehicle> page = new PageImpl<>(fake);
        Pageable pageable = PageRequest.of(0, 12);
        Mockito.when(repo.findByDealerId("validUserId",pageable))
                .thenReturn(Utils.getVehicleList());
        Mockito.when(repo.findByDealerId("invalidUserId",pageable)).thenReturn(page);
    }

    @Test
    void shouldReturnVehicleList() throws ServiceException {
        Page<Vehicle> actual = service.getVehicleListByDealerId("validUserId",1);
        Page<Vehicle> expected = Utils.getVehicleList();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnServiceException_WhenNoMatchingIdFound() {
        assertThrows(ServiceException.class, () -> service.getVehicleListByDealerId("invalidUserId",1));
    }
}
