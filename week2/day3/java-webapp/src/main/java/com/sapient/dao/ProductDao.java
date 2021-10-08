package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Product;

public interface ProductDao {
    public Product getById(Integer id) throws DaoException;

    public List<Product> getAllProducts() throws DaoException;

    public List<Product> getProductsByPriceRange(Double min, Double max);
}
