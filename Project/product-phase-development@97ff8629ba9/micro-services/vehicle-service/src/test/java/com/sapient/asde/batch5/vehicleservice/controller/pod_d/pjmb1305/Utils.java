package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1305;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

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
        if(isDealer){
            claims.put("userType", "dealer");
        }
        else{
            claims.put("userType","customer");
        }
        
        claims.put("exp", "324323");
        claims.put("userId", "validId");


        claims.put("iat", "234234234");
        claims.put("email", "email");
        log.info("Test Claims, {}", claims);
        return claims;

    }

    public static Vehicle getVehicle(){
        Vehicle vehicle=new Vehicle();
        vehicle.setId("id");
        vehicle.setDealerId("userId");
        vehicle.setBrand("Maruti");
        return vehicle;


    }
    
    public static Vehicle getUpdatedVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId("id");
        vehicle.setDealerId("userId");
        vehicle.setBrand("Maruti1");
        return vehicle;

    }
    
}
