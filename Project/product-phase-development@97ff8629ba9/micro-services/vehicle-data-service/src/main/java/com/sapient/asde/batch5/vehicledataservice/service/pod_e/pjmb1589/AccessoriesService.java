package com.sapient.asde.batch5.vehicledataservice.service.pod_e.pjmb1589;

import java.io.IOException;

import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

public interface AccessoriesService {

    Boolean store(MultipartFile file, String userId, String originalFilename) throws ServiceException,ParseException,IOException;

}
