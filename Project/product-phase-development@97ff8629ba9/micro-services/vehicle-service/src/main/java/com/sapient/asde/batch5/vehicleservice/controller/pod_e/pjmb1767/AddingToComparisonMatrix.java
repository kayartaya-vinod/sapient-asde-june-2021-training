package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1767;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1767.BrandAndModelService;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import com.sapient.asde.batch5.vehicleservice.entity.Select;

@Slf4j
@RestController
@RequestMapping("/api/vehicles/comparison")
public class AddingToComparisonMatrix {
    @Autowired
    BrandAndModelService service;

    private static final String SUCCESS = "success";
    private static final String DATA = "data";

    /**
     * @Author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
     */
    @GetMapping
    public ResponseEntity<Object> getModelAndBrand(@RequestParam String brand,@RequestParam String model,
    @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) throws ServiceException{
        log.info("requested Brand is {} and Model is {}",brand,model);
        Map<String, Object> resp = new HashMap<>();
        if(brand.isEmpty()){
            List<Select> m = service.getBrand();
            resp.put(DATA, m);
        }else{
            List<Select>m = service.getBrandAndModel(brand, model,page);
            resp.put(DATA, m);
        }
        resp.put(SUCCESS, true);
        return ResponseEntity.ok(resp);
    }
}
