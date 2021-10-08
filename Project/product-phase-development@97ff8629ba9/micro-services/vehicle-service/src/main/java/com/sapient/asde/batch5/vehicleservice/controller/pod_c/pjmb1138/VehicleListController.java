package com.sapient.asde.batch5.vehicleservice.controller.pod_c.pjmb1138;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1138.VehicleListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mutharsan E mutharasan.e@publicissapient.com
 */

@Slf4j
@RestController
@RequestMapping("/api/vehicles")
public class VehicleListController {
    @Autowired
    VehicleListService service;

    @GetMapping
    public ResponseEntity<Page<Vehicle>> getVehicleList(
            @RequestParam(required = false, defaultValue = "1") Integer page) throws ServiceException {
        log.info("GET /api/page - Getting the List of Vehicles with pagination");
        log.info("We are at page:{}", page);
        return ResponseEntity.ok(service.getVehiclewithPagination(page));
    }
}
