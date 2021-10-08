package com.sapient.asde.batch5.vehicleservice.repository;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>,CustomVehicleRepository {

    public Page<Vehicle> findByDealerId(String dealerId, Pageable page) throws ServiceException;
    
    /**
     * @Author Deepthi.S deepthi.s@publicissapient.com
     */
     public List<Vehicle> findVehiclesByDealerId(String dealerId) throws ServiceException;


    /**
     * @Author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
     */
    public List<Vehicle> findByBrandAndModel(String brand,String model) throws ServiceException;

    /**
     * @Author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
     */
    @Aggregation("{ '$group': { '_id' : '$brand' } }")
    public List<String> groupByBrand();
    
    /**
     * @Author Akhilesh Kushwaha akhilesh.kushwaha1@publicissapient.com
     */
    public List<Vehicle> findByBrand(String brand,Pageable page);
}
