package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Product;

public interface ProductDao {
    public List<Product> getAll();

    public List<Product> getByBrand(String brand);

    public List<Product> getByCategory(String category);

    public Product getById(Integer id);
}
