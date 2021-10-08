/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/
package com.sapient.asde.batch5.vehicledataservice.controller.pod_b.pjmb1453;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;
import com.sapient.asde.batch5.vehicledataservice.service.pod_b.pjmb1453.GetVehicleUploadedFilesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/data/my-uploads")
public class GetVehicleUploadedFilesController {

    private static final String SUCCESS = "success";
    private static final String DATA = "data";
    private static final String MESSAGE = "message";

    @Autowired
    GetVehicleUploadedFilesService service;

    @GetMapping("/{dealerId}")
    public ResponseEntity<Object> getUploadedFiles(@PathVariable String dealerId,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) throws ServiceException {

        log.trace("Inside Vehicle data service Controller to get uploaded files");

        Map<String, Object> mp = new HashMap<>();

        if (claims == null) {
            mp.put(SUCCESS, false);
            mp.put(MESSAGE, "Authorization header may be missing or invalid try.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mp);
        }
        String userType = (String) claims.get("userType");
        if (!userType.equalsIgnoreCase("dealer")) {
            mp.put(SUCCESS, false);
            mp.put(MESSAGE, "Permession Denied : For uploading user has to be dealer");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mp);
        }

        if (!dealerId.equals(claims.get("userId"))) {
            mp.put(SUCCESS, false);
            mp.put(MESSAGE, "Permession Denied : You cannot access others uploads");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mp);
        }

        try {

            List<VehicleUpload> data = service.getVehicleUploadedFiles(dealerId);
            mp.put(SUCCESS, true);
            mp.put(DATA, data);
            return ResponseEntity.ok(mp);

        } catch (Exception e) {

            mp.put(SUCCESS, false);
            mp.put(MESSAGE, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mp);
        }

    }

}
