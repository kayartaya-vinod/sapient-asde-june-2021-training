package com.sapient.asde.batch5.vehicleservice.repository;

import java.util.List;

import com.sapient.asde.batch5.vehicleservice.entity.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Deepthi S deepthi.s@publicissapient.com
 */
@Slf4j
public class CustomVehicleRepositoryImpl implements CustomVehicleRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    
    @Override
    public Page<Vehicle> findByAdvSearch(String[] vehicleType, String[] brand,Double minPrice, Double maxPrice,
                                        String[] color, String[] fuelType, Integer airBagCount, Integer year, Integer page) {
                                            
        Pageable pageable = PageRequest.of(page-1, 12);
        Query dynamicQuery = new Query().with(pageable);

        if (brand != null) {
            Criteria brandCriteria = Criteria.where("brand").in((Object[])brand);
            dynamicQuery.addCriteria(brandCriteria);
        }
        if (vehicleType != null) {
            Criteria typeCriteria = Criteria.where("vehicleType").in((Object[])vehicleType);
            dynamicQuery.addCriteria(typeCriteria);
        }
        if (color != null) {
            Criteria colorCriteria = Criteria.where("color").in((Object[])color);
            dynamicQuery.addCriteria(colorCriteria);
        }
        if (fuelType != null) {
            Criteria fuelCriteria = Criteria.where("fuelType").in((Object[])fuelType);
            dynamicQuery.addCriteria(fuelCriteria);
        }
        if (airBagCount != null) {
            Criteria airBagCriteria = Criteria.where("airBagCount").is(airBagCount);
            dynamicQuery.addCriteria(airBagCriteria);
        }
        if (year != null) {
            Criteria yearCriteria = Criteria.where("year").gte(year);
            dynamicQuery.addCriteria(yearCriteria);
        }

        if ((minPrice != null) && (maxPrice != null)) {

            Criteria priceCriteria = (Criteria.where("price").gte(minPrice)
                    .andOperator(Criteria.where("price").lte(maxPrice)));

            dynamicQuery.addCriteria(priceCriteria);
        }

        log.info("Dynamic Query is {}", dynamicQuery);
        List<Vehicle> resultList = mongoTemplate.find(dynamicQuery.collation(Collation.of("en").strength(Collation.ComparisonLevel.secondary())), Vehicle.class);
        return PageableExecutionUtils.getPage(resultList, pageable,
                () -> mongoTemplate.count(dynamicQuery, Vehicle.class));
    }
}
