package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1220;


import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1220.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vehicles/search")
public class SearchController {

  @Autowired
  SearchService sch;

  @GetMapping
  public ResponseEntity<Object> getByText(@RequestParam String q, @RequestParam(required = false, defaultValue = "1") Integer page) throws ServiceException {
    if (q.equals("")) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nothing entered");

    } else {
      List<Vehicle> vehicle = sch.findVehicleByText(q,page);
      return ResponseEntity.ok(vehicle);
    }
  }
}
