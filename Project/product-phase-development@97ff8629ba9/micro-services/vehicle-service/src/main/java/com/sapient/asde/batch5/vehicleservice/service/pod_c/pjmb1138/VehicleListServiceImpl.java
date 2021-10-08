package com.sapient.asde.batch5.vehicleservice.service.pod_c.pjmb1138;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Mutharsan E mutharasan.e@publicissapient.com
 */

@Slf4j
@Service
public class VehicleListServiceImpl implements VehicleListService {

    @Autowired
    VehicleRepository vehiclerepo;

    //@Cacheable(value ="VehicleList")
    @Override
    public Page<Vehicle> getVehiclewithPagination(Integer page) throws ServiceException {
        Pageable pageable = PageRequest.of(page - 1, 12, Sort.by("_id"));
        log.info("In VehicleListControllerImpl - vehicle-service");
        log.info("Getting Vehicle data for this {}", page);
        return vehiclerepo.findAll(pageable);
    }

}
