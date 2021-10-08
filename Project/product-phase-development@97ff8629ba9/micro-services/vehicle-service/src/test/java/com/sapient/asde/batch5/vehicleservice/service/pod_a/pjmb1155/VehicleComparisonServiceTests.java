package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1155;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleComparisonRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Abhishek Singh abhishek.singh8@publicissapient.com
 */
@SpringBootTest
class VehicleComparisonServiceTests {
  @Autowired
  @Qualifier("pjmb1155_vehiclecomparisonserviceimpl")
  private VehicleComparisonService service;

  @MockBean
  private VehicleComparisonRepository comparisonRepo;

  private VehicleComparison comp;
  private VehicleComparison errorComp;

  private String compId;
  private String userId;
  private String errorId;

  @BeforeEach
  void setup() {
    compId = "compId";
    userId = "userId";
    errorId = "errorId";
    comp = new VehicleComparison(compId, userId, null, null, null);
    errorComp = new VehicleComparison(compId, errorId, null, null, null);
  }

  @Test
  void shouldReturnNothing_whenComparisonDeleted() throws ServiceException {
    Mockito.when(comparisonRepo.findById(compId)).thenReturn(Optional.of(comp));
    Mockito.doNothing().when(comparisonRepo).deleteById(compId);
    assertDoesNotThrow(() -> service.deleteComparisonMatrixById(compId, userId));
  }

  @Test
  void shouldThrowServiceException_whenIdOrUserIdIsEmpty() throws ServiceException {
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById("", "userId"));
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById(" ", "userId"));
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById(null, "userId"));
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById("id", ""));
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById("id", " "));
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById("id", null));
  }

  @Test
  void shouldThrowServiceException_whenIdNotFound() throws ServiceException {
    Mockito.when(comparisonRepo.findById(compId)).thenReturn(Optional.ofNullable(null));
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById(compId, userId));
  }

  @Test
  void shouldThrowServiceException_whenUserIdIsDifferent() throws ServiceException {
    Mockito.when(comparisonRepo.findById(compId)).thenReturn(Optional.of(errorComp));
    assertThrows(ServiceException.class, () -> service.deleteComparisonMatrixById(compId, userId));
  }

}
