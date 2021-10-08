package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1151;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;


public final class Utils {
    public static Map<String, Object> getClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "customer");
        claims.put("exp", "324323");
        claims.put("userId", "id");
        claims.put("iat", "234234234");
        claims.put("email", "email");
        return claims;
    }


    public static VehicleComparison getStoreObject() {
       
        List<String> vehicleIds = new ArrayList<>();
        vehicleIds.add("id1");
        vehicleIds.add("id2");

        Date date = Date.from(Instant.parse("2000-01-01T00:00:00.000Z"));

        VehicleComparison vehicle =new VehicleComparison();
          
        vehicle.setId("id");
        vehicle.setUserId("u1");
        vehicle.setComparisonMatrixName("Name1");
        vehicle.setCreatedDate(date);
        
        vehicle.setVehicleIds(vehicleIds);
       
        return vehicle;
    }

    public static Map<String, Object> getStoreVehicle() {
        Map<String, Object> vehicle = new HashMap<>();
        List<String> vehicleIds = new ArrayList<>();
        vehicleIds.add("id1");
        vehicleIds.add("id2");

        Date date = Date.from(Instant.parse("2000-01-01T00:00:00.000Z"));

        vehicle.put("id", 1);
        vehicle.put("userId", "u1");
        vehicle.put("comparisonMatrixName", "Name1");
        vehicle.put("createdDate", date);
        vehicle.put("vehicleIds", vehicleIds);
        
        return vehicle;
    }
    
}
