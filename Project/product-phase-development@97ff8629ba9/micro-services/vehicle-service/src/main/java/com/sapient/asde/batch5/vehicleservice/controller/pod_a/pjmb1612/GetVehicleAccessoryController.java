package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1612;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_a.pjmb1612.GetVehicleAccessoryService;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Neha neha1@publicissapient.com
 */

@Slf4j
@RestController
@RequestMapping("/api/vehicles/accessory")
public class GetVehicleAccessoryController {

    @Autowired
    GetVehicleAccessoryService service;

    private static final String SUCCESS = "success";
    private static final String DATA = "data";

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> vehicleAccessory(@PathVariable String id) throws ServiceException {

        log.trace("GET /api/vehicles/accessory/" + id);

        Map<String, Object> map = new HashMap<>();

        List<JsonObject> jbl = service.getVehicleAccessory(id);

        map.put(SUCCESS, true);

        if (jbl.isEmpty()) {

            map.put(DATA, "{}");
        } else {
            map.put(DATA, jbl.get(0));
        }
        return map;
    }

}
