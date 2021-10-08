package com.sapient.asde.batch5.vehicledataservice.service.pod_e.pjmb1589;


import java.io.IOException;

import org.json.simple.parser.ParseException;


import com.sapient.asde.batch5.vehicledataservice.repository.VehicleAccessoryRepository;
import com.sapient.asde.batch5.vehicledataservice.service.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AccessoriesServiceImpl implements AccessoriesService {
    @Autowired
    VehicleAccessoryRepository repo;
    

    @Override
    public Boolean store(MultipartFile file, String userId, String fileName)
            throws ServiceException, ParseException, IOException {
       return repo.saveInDb(file, userId);
    }
}