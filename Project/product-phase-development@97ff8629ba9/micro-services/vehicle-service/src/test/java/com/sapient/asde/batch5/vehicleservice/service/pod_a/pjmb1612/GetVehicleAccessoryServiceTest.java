package com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1612;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import com.sapient.asde.batch5.vehicleservice.repository.VehicleAccessoryRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.bson.Document;
import org.bson.json.JsonObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

/**
 * @author Neha neha1@publicissapient.com
 */

@SpringBootTest(properties = "spring.main.lazy-initialization=true", classes = { GetVehicleAccessoryServiceImpl.class })
class GetVehicleAccessoryServiceTest {

    @MockBean
    VehicleAccessoryRepository repo;

    @Autowired
    GetVehicleAccessoryService service;

    private final String VALID_ID = "VALID_ID";

    @Test
    void contextLoads() throws Exception {
        assertNotNull(service);
    }

    @Test
    void shouldGetVehiclesAccessoryById() throws ServiceException {

        JsonObject jb = new JsonObject("{\name\": \"Car Vacuum Cleaner 120W\"}");
        Document doc = new Document();
        doc.append("results", jb);
        AggregationResults ag = new AggregationResults(Arrays.asList(jb), doc);
        Mockito.when(repo.vehicleAccessory(VALID_ID)).thenReturn(ag.getMappedResults());

        assertNotNull(service.getVehicleAccessory(VALID_ID));
        assertEquals(ag.getMappedResults(), service.getVehicleAccessory(VALID_ID));
    }

    @Test
    void shouldThrowServiceException_whenIdIsEmpty() throws ServiceException {

        assertThrows(ServiceException.class, () -> service.getVehicleAccessory(""));
        assertThrows(ServiceException.class, () -> service.getVehicleAccessory(" "));
        assertThrows(ServiceException.class, () -> service.getVehicleAccessory(null));

    }

}
