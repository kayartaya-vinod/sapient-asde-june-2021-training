package com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1302;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.entity.Dealer;
import com.sapient.asde.batch5.vehicledataservice.entity.VehicleDownload;
import com.sapient.asde.batch5.vehicledataservice.repository.DealerRepository;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleDownloadRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;
import com.sapient.asde.batch5.vehicledataservice.utils.ApacheCommonsCsvUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Yogeshwar Chaturvedi yogeshwar.chaturvedi@publicissapient.com
 */

@Slf4j
@Service("VehicleDownloadService")
public class VehicleDownloadServiceImpl implements VehicleDownloadService {

    @Autowired
    VehicleDownloadRepository dao;

    @Autowired
    DealerRepository dealerRepository;

    @Override
    public void getAllVehicles(Writer writer, String userId) throws ServiceException {
        log.info("Inside Vehicle data download service Implementation");
        Dealer dealer = dealerRepository.findByUserId(userId);
        log.info("Dealer = {} ", dealer);
        if (dealer == null)
            throw new ServiceException("Dealer Not Found");
        try {
            log.info("Dealer List = {} ", dao.findByDealerId(dealer.getId()));
            ApacheCommonsCsvUtils.vehiclesToCsv(writer, dao.findByDealerId(dealer.getId()));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void getVehiclesById(List<String> id, Writer writer) throws ServiceException {
        List<VehicleDownload> result = new ArrayList<>();
        for (String i : id) {
            log.info("vehicle = {} ", dao.findById(i).get().toString());
            result.add(dao.findById(i).get());
        }
        try {
            ApacheCommonsCsvUtils.vehiclesToCsv(writer, result);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
