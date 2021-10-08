
package com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1299;

import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicledataservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleRepository;
import com.sapient.asde.batch5.vehicledataservice.repository.VehicleUploadRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;
import com.sapient.asde.batch5.vehicledataservice.utils.ApacheCommonsCsvUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.util.Pair;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Mutharasan E mutharasan.e@publicissapient.com
 */

@Slf4j
@Service
public class VehicleUploadServiceImpl implements VehicleUploadService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleUploadRepository vehicleUploadRepository;

    @Override
    public Map<Object, Object> store(InputStream file, String userId, String fileName) throws ServiceException {
        // Using ApacheCommons Csv Utils to parse CSV file
        log.info("Inside Vehicle data upload service Implementation");
        String success = "Success";
        String ur = "upload-ratio";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        String dt = "date-time";
        String dateTime = dtf.format(now);
        String message = "message";
        String noofdoc = "document-uploaded";

        Pair<Integer, List<Vehicle>> pair = ApacheCommonsCsvUtils.parseCsvFile(file, userId);
        Integer lines = pair.getFirst();
        List<Vehicle> vehicles = pair.getSecond();
        log.info("VehicleList={}", vehicles);
        if (vehicles.isEmpty()) {
            Map<Object, Object> result = new HashMap<>();
            result.put(success, false);
            result.put(ur, "0%");
            result.put(dt, dateTime);
            result.put(message, "Zero File Uploaded");
            result.put(noofdoc, 0);
            return result;
        }
        long countBefore = vehicleRepository.countByDealerId(userId);
        try {
            vehicleRepository.saveAll(vehicles);
            long countAfter = vehicleRepository.countByDealerId(userId);
            Double successRatio = ((double) (countAfter - countBefore) / lines) * 100;
            VehicleUpload uploadMetaData = new VehicleUpload(userId, fileName, dateTime, vehicles.size(), successRatio);
            vehicleUploadRepository.save(uploadMetaData);
            Map<Object, Object> result = new HashMap<>();
            result.put(success, true);
            result.put(ur, successRatio + "%");
            result.put(dt, dateTime);
            result.put(message, "File Upload success");
            result.put(noofdoc, countAfter - countBefore);
            return result;
        } catch (Exception e) {
            long countAfter = vehicleRepository.countByDealerId(userId);
            Double successRatio = ((double) (countAfter - countBefore) / lines) * 100;
            VehicleUpload uploadMetaData = new VehicleUpload(userId, fileName, dtf.format(now), vehicles.size(),
                    successRatio);
            vehicleUploadRepository.save(uploadMetaData);
            Map<Object, Object> result = new HashMap<>();
            result.put(success, false);
            result.put(ur, successRatio + "%");
            result.put(dt, dateTime);
            result.put(message, e.getMessage());
            result.put(noofdoc, countAfter - countBefore);
            return result;
        }
    }

}
