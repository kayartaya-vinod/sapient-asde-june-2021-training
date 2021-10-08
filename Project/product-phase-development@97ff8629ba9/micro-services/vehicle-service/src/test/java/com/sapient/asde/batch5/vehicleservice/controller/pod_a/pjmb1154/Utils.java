package com.sapient.asde.batch5.vehicleservice.controller.pod_a.pjmb1154;


import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleComparison;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Utils {
  
  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Function to get the claims to mock the filter
   * Returns {@Map<String, Object>}
   */
  public static Map<String, Object> getClaims(){
    Map<String, Object> claims = new HashMap<>();
    claims.put("firstName", "Name");
    claims.put("userType", "customer");
    claims.put("exp", "324323");
    claims.put("userId", "id");
    claims.put("iat", "234234234");
    claims.put("email", "email");
    log.info("Test Claims, {}", claims);
    return claims;
  }


  /**
   * Function to get test data for Vehicle Comparison Matrix
   * Returns {@VehicleComparison)
   */
  public static VehicleComparison getComparisonMatrix(){
    VehicleComparison vcmp = new VehicleComparison();
    List<String> vids = Arrays.asList("id1", "id2", "id3");  
    Date date = Date.from(Instant.parse("2000-01-01T00:00:00.000Z"));
    vcmp.setComparisonMatrixName("comparisonMatrixName");
    vcmp.setCreatedDate(date);
    vcmp.setId("id");
    vcmp.setUserId("userId");
    vcmp.setVehicleIds(vids);
    log.info("Test VehicleComparison Matrix, {}", vcmp);
    return vcmp;
  }
}
