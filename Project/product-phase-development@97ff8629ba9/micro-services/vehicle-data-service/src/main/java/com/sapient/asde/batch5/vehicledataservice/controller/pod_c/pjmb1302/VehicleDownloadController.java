package com.sapient.asde.batch5.vehicledataservice.controller.pod_c.pjmb1302;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1302.VehicleDownloadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Yogeshwar Chaturvedi yogeshwar.chaturvedi@publicissapient.com
 */

@Slf4j
@RestController
@RequestMapping("/api/data/download/csv")
public class VehicleDownloadController {
    @Autowired
    VehicleDownloadService service;

    String textcsv = "text/csv";
    String contentDisposition = "Content-Disposition";
    String attachment = "attachment; filename=vehicles.csv";

    @GetMapping("/all")
    public ResponseEntity<String> downloadAll(HttpServletResponse response,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) {

        if (claims == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Permession Denied : Authorization header may be missing or invalid");

        log.info("Inside Vehicle data service Controller for downloading all vehicles");
        log.info("Map from claims :{}", claims.toString());

        String userId = claims.get("userId").toString();
        String userType = claims.get("userType").toString();

        if (userType.equalsIgnoreCase("dealer")) {
            log.info("User Type is dealer");
            response.setContentType(textcsv);
            response.setHeader(contentDisposition, attachment);
            try {
                service.getAllVehicles(response.getWriter(), userId);
                return ResponseEntity.ok("File download successfully");
            } catch (Exception e) {
                return ResponseEntity.ok("Error" + e.getMessage());
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Permession Denied : For Uploading user has to be dealer");
        }

    }

    @GetMapping()
    public ResponseEntity<String> downloadWithIds(HttpServletResponse response, @RequestParam List<String> ids,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) {

        if (claims == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Permession Denied : Authorization header may be missing or invalid");

        log.info("Inside Vehicle data service Controller for downloading vehicles by vehicle ids");
        log.info("Map from claims :{}", claims.toString());

        String userType = claims.get("userType").toString();
        response.setContentType(textcsv);
        response.setHeader(contentDisposition, attachment);
        if (userType.equalsIgnoreCase("dealer")) {
            log.info("User Type is dealer");
            response.setContentType(textcsv);
            response.setHeader(contentDisposition, attachment);
            try {
                service.getVehiclesById(ids, response.getWriter());
                return ResponseEntity.ok("File download successfully");
            } catch (Exception e) {
                return ResponseEntity.ok("Error" + e.getMessage());
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Permession Denied : For Uploading user has to be dealer");
        }
    }

}
