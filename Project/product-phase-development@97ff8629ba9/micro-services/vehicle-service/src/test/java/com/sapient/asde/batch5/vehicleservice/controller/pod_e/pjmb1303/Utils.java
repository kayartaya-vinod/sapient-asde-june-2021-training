package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1303;


import java.util.HashMap;

import java.util.Map;




import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Function to get the claims to mock the filter Returns {@Map<String, Object>}
     */
    public static Map<String, Object> getClaims(Boolean isValidDealerId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        
        if(isValidDealerId)
        {
            claims.put("userType", "dealer");
            claims.put("userId", "id");
        }
        else{
            claims.put("userType", "customer");
            claims.put("userId", "invalid");
        }
        claims.put("iat", "234234234");
        claims.put("email", "email");
        log.info("Test Claims, {}", claims);
        return claims;
    }
}
