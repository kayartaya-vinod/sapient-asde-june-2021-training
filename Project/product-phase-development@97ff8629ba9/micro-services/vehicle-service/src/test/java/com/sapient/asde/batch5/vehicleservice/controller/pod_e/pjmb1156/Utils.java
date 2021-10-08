package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1156;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Function to get the claims to mock the filter Returns {@Map<String, Object>}
     */
    public static Map<String, Object> getClaims(Boolean isValidUserId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "1");
        claims.put("exp", "324323");
        if(isValidUserId){
            claims.put("userId", "id");
        }
        else{
            claims.put("userId", "invalid");
        }
        claims.put("iat", "234234234");
        claims.put("email", "email");
        log.info("Test Claims, {}", claims);
        return claims;
    }

    /**
     * Function to get test data for comparison matrix
     * {@VehicleComparison)
     */
    public static VehicleComparison getVehicleComparison() {
        VehicleComparison vcmp = new VehicleComparison();
        List<String> vids = Arrays.asList("1");
        Date date = Date.from(Instant.parse("2000-01-01T00:00:00.000Z"));
        vcmp.setComparisonMatrixName("comparisonMatrixName");
        vcmp.setCreatedDate(date);
        vcmp.setId("hjasghdakjshdkj");
        vcmp.setUserId("1");
        vcmp.setVehicleIds(vids);
        log.info("Test VehicleComparison Matrix, {}", vcmp);
        return vcmp;
    }

     /**
     * Function to get test data for vehicle
     * {@VehicleComparison)
     */

    public static Vehicle getVehicle() {
        Vehicle vehicle =new Vehicle();
        vehicle.setBrand("Tata");
        return vehicle;
    }
}