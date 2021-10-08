package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1151;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleComparisonRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Neha Neha1@publicissapient.com
 */


@SpringBootTest
 class StoreVehicleComparisonTest {

    @Autowired
    StoreVehicleComparisonService service;

    @MockBean
    VehicleComparisonRepository repo;

    private String userId;

    VehicleComparison v1;

    @BeforeEach
    void setup() {
        userId = "userId";

        List<String> vehicleIds = new ArrayList<>();
        vehicleIds.add("id1");
        vehicleIds.add("id2");
        Date date = Date.from(Instant.parse("2000-01-01T00:00:00.000Z"));
        v1 = new VehicleComparison("id", userId, vehicleIds, "comparisonMatrixName", date);

        Mockito.when(repo.save(v1)).thenReturn(v1);

    }

    @Test
    void shouldStoreVehicle() throws ServiceException {
        VehicleComparison actual = service.storeVehicle(v1,userId);
        VehicleComparison expected = v1;
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowServiceException_whenIdOrUserIdIsEmpty() throws ServiceException {
        assertThrows(ServiceException.class, () -> service.storeVehicle(v1, ""));
        assertThrows(ServiceException.class, () -> service.storeVehicle(v1, " "));
        assertThrows(ServiceException.class, () -> service.storeVehicle(v1,null));
        
    }

}
