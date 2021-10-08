package com.sapient.asde.batch5.vehicledataservice.controller.pod_c.pjmb1299;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicledataservice.service.pod_c.pjmb1299.VehicleUploadService;
import com.sapient.asde.batch5.vehicledataservice.utils.ApacheCommonsCsvUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/data/upload")
public class VehicleUploadController {
    @Autowired
    VehicleUploadService service;

    @PostMapping("/csv")
    public ResponseEntity<Object> uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) {

               
        log.info("Inside Vehicle data service Controller for upload");
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid try.");
        }
        log.info("Map from claims :{}", claims.toString());
        String userId = claims.get("userId").toString();
        String userType = claims.get("userType").toString();
        if(userType.equalsIgnoreCase("dealer"))
        {
            log.info("User Type is dealer");
        // Checking the upload-file's name before processing
            if (csvfile.getOriginalFilename().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    " File not selected to upload! Please upload the file");
            }

        // checking the upload file's type is CSV or NOT

        if (!ApacheCommonsCsvUtils.isCSVFile(csvfile)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(csvfile.getOriginalFilename() + ": Error: this is not a CSV file! ");
        }

        try {
            // save file data to database
            Map result = new HashMap<>();
                result = service.store(csvfile.getInputStream(),userId,csvfile.getOriginalFilename());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(csvfile.getOriginalFilename()+" Upload failed :"+e.getMessage());
        }
    }
    else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Permession Denied : For Uploading user has to be dealer");
    }
}

   
}
