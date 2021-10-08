/**
@Author <Sanchit Mahto> <sanchit.mahto@publicissapient.com>
*/
package com.sapient.asde.batch5.vehicledataservice.repository;

import java.util.List;

import com.sapient.asde.batch5.vehicledataservice.entity.VehicleUpload;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleUploadRepository extends MongoRepository<VehicleUpload, String> {

    @Query("{'dealerId':?0}")
    public List<VehicleUpload> getFilesByDealerId(String dealerId);
}
