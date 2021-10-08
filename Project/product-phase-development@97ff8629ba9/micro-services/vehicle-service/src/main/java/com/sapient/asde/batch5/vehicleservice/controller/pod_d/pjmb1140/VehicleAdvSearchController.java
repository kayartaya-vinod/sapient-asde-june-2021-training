package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1140;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1140.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Deepthi S deepthi.s@publicissapient.com
 */
@Slf4j
@RestController
@RequestMapping("/api/vehicles")
public class VehicleAdvSearchController {
    
    @Autowired
    private VehicleService service;
    
    private static final String SUCCESS = "success";
    private static final String DATA = "data";

    @GetMapping("/advanced-search")
    public ResponseEntity<Object> advancedSearch(
            @RequestParam(name = "vehicle_type", required = false) String[] vehicleType,
            @RequestParam(name = "brand", required = false) String[] brand,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "color", required = false) String[] color,
            @RequestParam(name = "fuel_type", required = false) String[] fuelType,
            @RequestParam(name = "air_bag", required = false) Integer airBagCount,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page
            ) throws ServiceException {

            log.info("GET /api/vehicles/advanced-search");
            Map<String, Object> resp = new HashMap<>();
            Page<Vehicle> vehicles= service.getByAdvancedSearch(vehicleType,brand,minPrice,maxPrice,color,fuelType,airBagCount,year,page);
            resp.put(SUCCESS, true);
            resp.put(DATA, vehicles);
            return ResponseEntity.ok(resp);

    }
}
