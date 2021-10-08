package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1143;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
 class GetVehicleByIdServiceTest {
    @Autowired
	GetVehicleByIdService service;

	@MockBean
	VehicleRepository dao;

	
	private final String VALID_ID = "VALID_ID";
  private final String INVALID_ID = "INVALID_ID";
  private final String NULL_ID = "";
  private final String space_ID = "   ";
  Vehicle v;

    @BeforeEach
	void setup() {
        v = new Vehicle();
        v.setDealerId("dealerId");
        v.setId("id");
        Mockito.when(dao.findById(VALID_ID)).thenReturn(Optional.of(v));
		Mockito.when(dao.findById(INVALID_ID)).thenReturn(Optional.empty());
    }
    @Test
	void shouldGetProductById() throws ServiceException {
Vehicle p = service.getVehicleById(VALID_ID);
		assertNotNull(p);
	}
    @Test
	void shouldReturnNullForInvalidId() {
        assertThrows(ServiceException.class, () -> service.getVehicleById(NULL_ID));
	}
    @Test
	void shouldThrowExceptionForInvalidId() {
        assertThrows(ServiceException.class, () -> service.getVehicleById(INVALID_ID));
	}
    @Test
	void shouldThrowExceptionForEmptyId() {
        assertThrows(ServiceException.class, () -> service.getVehicleById(space_ID));
	}

}
