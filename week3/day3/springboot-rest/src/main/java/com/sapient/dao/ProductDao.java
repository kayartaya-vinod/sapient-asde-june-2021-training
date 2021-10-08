package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {
    // findByXxx
    // Xxx --> a property of the entity class such as "brand" or
    // "category"
    public List<Product> findByBrand(String brand);

    @Query("from Product where unitPrice between ?1 and ?2")
    public List<Product> findByPriceRange(Double min, Double max);
}
