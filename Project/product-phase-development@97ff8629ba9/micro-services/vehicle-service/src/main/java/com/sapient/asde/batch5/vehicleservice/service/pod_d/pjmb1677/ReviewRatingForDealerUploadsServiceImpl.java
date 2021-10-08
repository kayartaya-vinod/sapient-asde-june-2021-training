package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1677;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sapient.asde.batch5.vehicleservice.entity.Feedback;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.FeedbackRepository;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Deepthi.S deepthi.s@publicissapient.com
 */
@Slf4j
@Service
public class ReviewRatingForDealerUploadsServiceImpl implements ReviewRatingForDealerUploadsService {

    @Autowired
    VehicleRepository vrepo;

    @Autowired
    FeedbackRepository fbrepo;

    @Autowired
    RestTemplate restTemplate;
    @Value("${custUrl}")
    String custUrl;

    @Override
    public Map<String, Object> getFeedbackForDealerUploads(String dealerId, Integer page) throws ServiceException {

        Pageable pageable = PageRequest.of(page - 1, 12);
        if (dealerId == null || dealerId.trim().isEmpty()) {
            log.trace("findVehiclesByDealerId dealerId not supplied. ");
            throw new ServiceException("Dealer id cannot be empty.", HttpStatus.BAD_REQUEST);
        }

      
            List<Vehicle> vehicles = vrepo.findVehiclesByDealerId(dealerId);

            if (vehicles.isEmpty()) {
                log.trace(" Vehicles for this dealer id not found.");
                throw new ServiceException("Vehicles for this dealer id not found.", HttpStatus.NOT_FOUND);
            }
            List<String> vids = vehicles.stream().map(Vehicle::getId).collect(Collectors.toList());

            Page<Feedback> feedbacks = fbrepo.findAllByVehicleIds(vids, pageable);

            log.trace("Feedbakcs for Vehicle ids for dealerId is {}", feedbacks.getContent());

            Map<String, Object> result = new LinkedHashMap<>();
            List<Map<String, Object>> fbWithName = new ArrayList<>();

            for (Feedback feedback : feedbacks) {
                Map<String, Object> resp = new HashMap<>();
                String name = restTemplate.getForObject(custUrl + feedback.getUserId(), String.class);
                String vehicleId = feedback.getVehicleId();
                Optional<Vehicle> v = vrepo.findById(vehicleId);
                String vName="";
                if(v.isPresent()){
                     vName = v.get().getModel();
                }
                resp.put("userName", name);
                resp.put("vehicleName", vName);
                resp.put("Feedback", feedback);
                fbWithName.add(resp);
            }
           
            result.put("feedbacks", fbWithName);
            result.put("page", feedbacks);
            log.trace("Result is {}", result);
            return result;
        
       
    }

}
