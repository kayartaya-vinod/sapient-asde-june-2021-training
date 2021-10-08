package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1154;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1154.Utils;
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
 * @author Shubham Chaudhary shubham.chaudhary@publicissapient.com
 */
@SpringBootTest
 class VehicleComparisonServiceTests {
  
  @Autowired
  VehicleComaprisonServiceImpl service;

  @MockBean
  VehicleComparisonRepository repo;

  @BeforeEach
  void setup(){
    Mockito.when(repo.findByIdAndUserId("validId", "validUserId")).thenReturn(Optional.of(Utils.getComparisonMatrix()));
    Mockito.when(repo.findByIdAndUserId("invalidId", "validUserId")).thenReturn(Optional.empty());
    Mockito.when(repo.findByIdAndUserId("validId", "invalidUserId")).thenReturn(Optional.empty());
    Mockito.when(repo.findByIdAndUserId("invalidId", "invalidUserId")).thenReturn(Optional.empty());
  }

  @Test
  void shouldReturnMatrix() throws ServiceException {
    VehicleComparison actual = service.getComparisonMatrix("validId", "validUserId");
    VehicleComparison expected = Utils.getComparisonMatrix();
    assertEquals(expected, actual);
  }


  @Test
  void shouldReturnServiceException_WhenNoMatchingIdFound(){
    assertThrows(ServiceException.class, () -> service.getComparisonMatrix("invalidId", "validUserId"));
    assertThrows(ServiceException.class, () -> service.getComparisonMatrix("validId", "invalidUserId"));
    assertThrows(ServiceException.class, () -> service.getComparisonMatrix("invalidId", "invalidUserId"));
  }
}
