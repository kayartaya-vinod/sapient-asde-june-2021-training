package com.sapient.asde.batch5.vehicleservice.controller.pod_d.pjmb1677;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Deepthi.S deepthi.s@publicissapient.com
 */
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
        if (isDealer) {
            claims.put("userType", "dealer");
        } else {
            claims.put("userType", "customer");
        }

        claims.put("exp", "324323");
        claims.put("userId", "validId");

        claims.put("iat", "234234234");
        claims.put("email", "email");
        log.info("Test Claims, {}", claims);
        return claims;

    }

    public static Map<String, Object> getFeedbacks() {
        Map<String, Object> feedbacks = new HashMap<>();

        feedbacks.put("feedbacks", "These are feedback objects");
        feedbacks.put("page", "page info");
        return feedbacks;
    }

}
