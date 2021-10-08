package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1612;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1612.GetVehicleAccessoryService;

import org.bson.Document;
import org.bson.json.JsonObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Neha neha1@publicissapient.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class GetVehicleAccessoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GetVehicleAccessoryService service;

    private final String VALID_ID = "VALID_ID";
    private final String INVALID_ID = "INVALID_ID";
    VehicleAccessory v;
    Document v1;

    @Test
    void shouldReturnOk_whenAttributesAreRequested() throws Exception {
        JsonObject jb = new JsonObject("{\"name\": \"Car Vacuum Cleaner 120W\"}");
        Mockito.when(service.getVehicleAccessory(VALID_ID)).thenReturn(Arrays.asList(jb));

        String url = "/api/vehicles/accessory/" + VALID_ID;

        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void shouldGiveStatusOkForValidId() throws Exception {
        String url = "/api/vehicles/accessory/" + VALID_ID;
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void shouldStatusNotFoundForInvalidId() throws Exception {
        String url = "/api/vehicles/accessory/" + INVALID_ID;
        Mockito.when(service.getVehicleAccessory(INVALID_ID))
                .thenThrow(new ServiceException("Id not valid ", HttpStatus.NOT_FOUND));
        mockMvc.perform(get(url)).andExpect(status().isNotFound());
    }

}
