package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1677;

import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * @Author Deepthi.S deepthi.s@publicissapient.com
 */
public class Utils {

    public static List<Vehicle> getVehicles(){
        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle();
        v1.setId("1");
        v1.setBrand("bmw");
        v1.setVehicleType("Sedan");
        v1.setDealerId("dealerId");
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(v1);

        v2.setId("2");
        v2.setBrand("Hyundai");
        v2.setVehicleType("Hatch back");
        v2.setDealerId("dealerId");
        vehicles.add(v2);
        return vehicles;
    }
    
    public static Page<Feedback> getFeedbacks(){
        Feedback fb = new Feedback();
        fb.setRating(5);
        fb.setReview("review1");
        fb.setVehicleId("1");
        fb.setId("id1");
        fb.setUserId("userId1");
        List<Feedback> fbs = new ArrayList<Feedback>();
        fbs.add(fb);

        Feedback fb2 = new Feedback();
        fb2.setRating(5);
        fb2.setReview("review2");
        fb2.setVehicleId("2");
        fb2.setId("id2");
        fb2.setUserId("userId2");
        fbs.add(fb2);
        Page<Feedback> fbPage = new PageImpl<>(fbs);
        return fbPage;
    }

}
