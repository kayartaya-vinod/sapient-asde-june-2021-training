package com.sapient.asde.batch5.vehicleservice.service.pod_b.pjmb1679;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.entity.VehicleAccessory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Utils {
    private Utils() {
        throw new IllegalStateException("Utility class");
    }
    public static Map<String, Object> getClaims(Boolean isValidUserId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", "Name");
        claims.put("userType", "dealer");
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

    public static Page<VehicleAccessory> getAccessoryList(){
        List<VehicleAccessory> result = new ArrayList<>();
        VehicleAccessory vh=new VehicleAccessory();
        vh.setDealerId("dealerId");
        vh.setId("id");
        vh.setDescription("Mud Flaps");
        vh.setName("Mud flaps for car ");
        List<String>pictureUrls=new ArrayList<>();
        pictureUrls.add("Url1");
        pictureUrls.add("Url2");
        vh.setPictureUrls(pictureUrls);
        result.add(vh);
        Page<VehicleAccessory> page = new PageImpl<>(result);
        return page;
    }
}

