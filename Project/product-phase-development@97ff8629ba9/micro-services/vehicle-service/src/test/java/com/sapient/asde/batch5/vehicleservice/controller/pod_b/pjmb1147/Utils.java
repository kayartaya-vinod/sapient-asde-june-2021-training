//@Author <Sumitesh Naithani> <sumitesh.naithani@publicissapient.com>
package com.sapient.asde.batch5.vehicleservice.controller.pod_b.pjmb1147;

import java.util.HashMap;
import java.util.Map;


public final class Utils {
    public static Map<String, Object> getClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "customer");
        claims.put("exp", "324323");
        claims.put("userId", "2");
        claims.put("iat", "234234234");
        claims.put("email", "email");
        return claims;
    }    
}
