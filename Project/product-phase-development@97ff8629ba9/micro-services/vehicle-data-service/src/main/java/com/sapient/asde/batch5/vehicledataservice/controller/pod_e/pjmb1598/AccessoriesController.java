package com.sapient.asde.batch5.vehicledataservice.controller.pod_e.pjmb1598;

import java.util.Map;

import com.sapient.asde.batch5.vehicledataservice.service.pod_e.pjmb1589.AccessoriesService;
import com.sapient.asde.batch5.vehicledataservice.utils.JsonUtilsFile;

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
public class AccessoriesController {
    @Autowired
    AccessoriesService service;

    @PostMapping("/json")
    public ResponseEntity<String> uploadSingleCSVFile(@RequestParam("jsonfile") MultipartFile jsonfile,
            @RequestAttribute(required = false, name = "claims") Map<String, Object> claims) {

        log.trace("Inside Vehicle data service Controller for upload" + jsonfile);
        if (claims == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authorization header may be missing or invalid try.");
        }

        if (JsonUtilsFile.isJsonFile(jsonfile)) {
            log.trace("Map from claims :{}", claims.toString());
            String userId = claims.get("userId").toString();
            String userType = claims.get("userType").toString();

            if (userType.equalsIgnoreCase("dealer")) {
                log.trace("User Type is dealer");

                try {

                    Boolean bool = service.store(jsonfile, userId, jsonfile.getOriginalFilename());
                    if (Boolean.TRUE.equals(bool)) {
                        return ResponseEntity.ok("File uploaded successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(jsonfile.getOriginalFilename() + " Upload failed necessory key not present");
                    }
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(jsonfile.getOriginalFilename() + " Upload failed, wrong format:");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Permession Denied : For Uploading user has to be dealer");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(jsonfile.getOriginalFilename() + ": Error: this is not a JSON file! ");
        }

    }

}
