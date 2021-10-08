package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1349;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Function to get the claims to mock the filter Returns {@Map<String, Object>}
     */
    public static Map<String, Object> getClaims(Boolean isDealer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userId", "validId");
        claims.put("exp", "324323");
        if(isDealer){
            claims.put("userType", "dealer");
        }
        else{
            claims.put("userType", "customer");
        }
        claims.put("iat", "234234234");
        claims.put("email", "email");
        log.info("Test Claims, {}", claims);
        return claims;
    }

    /**
     * Function to get test data for Vehicle List 
     */

    public static Page<Vehicle> getVehicleList(){
        List<Vehicle> result = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setId("id");
        vehicle.setDealerId("dealerId");
        vehicle.setVehicleType("SUV");
        vehicle.setBrand("Honda");
        vehicle.setColor("red");
        result.add(vehicle);
        Page<Vehicle> page = new PageImpl<>(result);
        return page;
    }
}
