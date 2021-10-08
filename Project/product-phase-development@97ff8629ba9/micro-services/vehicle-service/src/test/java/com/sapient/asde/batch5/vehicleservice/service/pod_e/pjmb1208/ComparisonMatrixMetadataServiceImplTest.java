package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1208;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
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
 * @author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
 */
@SpringBootTest
class ComparisonMatrixMetadataServiceImplTest {

    @Autowired
    ComparisonMatrixMetadataServiceImpl service;

    @MockBean
    VehicleComparisonRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
        List<VehicleComparison> fake = new ArrayList<>();
        
        Mockito.when(repo.getByUserId("validUserId"))
                .thenReturn(Utils.getMatrixMetadata());
        Mockito.when(repo.getByUserId("invalidUserId")).thenReturn(fake);
    }

    @Test
    void shouldReturnMatrixMetadata() throws ServiceException {
        List<VehicleComparison> actual = service.getMetadataByUserId("validUserId");
        List<VehicleComparison> expected = Utils.getMatrixMetadata();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnServiceException_WhenNoMatchingIdFound() {
        assertThrows(ServiceException.class, () -> service.getMetadataByUserId("invalidUserId"));
    }
}
