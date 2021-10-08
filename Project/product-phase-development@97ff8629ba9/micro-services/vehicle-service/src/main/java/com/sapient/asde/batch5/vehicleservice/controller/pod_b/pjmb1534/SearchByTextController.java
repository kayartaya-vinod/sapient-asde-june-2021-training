/**
 * @author Sakshi Yadav  sakshi.yadav@publicissapient.com
 */


package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1534;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1534.SearchByTextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/vehicles/accessories")
public class SearchByTextController {

  private static final String SUCCESS = "success";
  private static final String DATA = "data";
  private static final String MESSAGE = "Message";
  @Autowired
  SearchByTextService s;

  @GetMapping("/search")
  public ResponseEntity<Object> getVehicleAccessoryByText(@RequestParam String q,
      @RequestParam(required = false, defaultValue = "1") Integer page) throws ServiceException {
    log.info("GET /api/vehicles/accessories/search");

    Map<String, Object> map = new HashMap<>();
    if (q.equals("")) {
      map.put(MESSAGE, "Nothing entered");
      map.put(SUCCESS, false);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
    List<VehicleAccessory> vehicleAcc = s.findAccessoryByText(q, page);
    if (vehicleAcc.isEmpty()) {
      map.put(MESSAGE, "No data found");
      map.put(SUCCESS, false);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
    
    map.put(DATA, vehicleAcc);
    map.put(SUCCESS, true);
    return ResponseEntity.ok(map);
  }
}