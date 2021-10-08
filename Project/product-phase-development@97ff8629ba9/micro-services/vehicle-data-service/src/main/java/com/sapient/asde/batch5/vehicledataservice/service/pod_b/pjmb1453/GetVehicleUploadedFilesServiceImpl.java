/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/
package com.sapient.asde.batch5.vehicledataservice.service.pod_b.pjmb1453;

import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleUploadRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetVehicleUploadedFilesServiceImpl implements GetVehicleUploadedFilesService {

    @Autowired
    VehicleUploadRepository vehicleUploadRepository;

    @Override
    public List<VehicleUpload> getVehicleUploadedFiles(String dealerId) throws ServiceException {

        log.trace("Inside get Vehicle data uploaded files service Implementation");
        return vehicleUploadRepository.getFilesByDealerId(dealerId);

    }

}
