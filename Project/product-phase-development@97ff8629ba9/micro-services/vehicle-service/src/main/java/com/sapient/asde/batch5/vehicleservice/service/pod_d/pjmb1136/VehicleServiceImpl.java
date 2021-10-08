package com.sapient.asde.batch5.vehicleservice.service.pod_d.pjmb1136;

import java.util.List;
import java.util.stream.Collectors;

import com.sapient.asde.batch5.vehicleservice.entity.SponsoredVehicles;
import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.SponsoredVehiclesRepository;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aravind M Krishnan aravind.m.krishnan@publicissapient.com
 */

@Slf4j
@Service("pjmb1136_VehicleServiceImpl")
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vr;

    @Autowired
    SponsoredVehiclesRepository sir;

    @Override
    public Iterable<Vehicle> getSponsoredVehicleByIdList() throws ServiceException {
        
        log.info("Inside sponsor Service Implementation");
        List<SponsoredVehicles> x = sir.findAll();

        List<String> ids = x.stream().map(SponsoredVehicles::getVid).collect(Collectors.toList());

        return vr.findAllById(ids);

    }

}
