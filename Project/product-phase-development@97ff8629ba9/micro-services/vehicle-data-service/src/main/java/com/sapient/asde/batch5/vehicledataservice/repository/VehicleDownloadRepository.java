package com.sapient.asde.batch5.vehicledataservice.repository;


import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.entity.VehicleDownload;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDownloadRepository extends MongoRepository<VehicleDownload, String> {
    public List<VehicleDownload> findByDealerId(String dealerId);
}
