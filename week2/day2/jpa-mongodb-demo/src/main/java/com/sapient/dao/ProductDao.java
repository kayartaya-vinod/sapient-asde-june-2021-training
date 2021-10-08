package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Product;

public interface ProductDao {
    // CRUD operations
    public void addProduct(Product product) throws DaoException;

    public Product getProduct(Integer id) throws DaoException;

    public void updateProduct(Product product) throws DaoException;

    public void deleteProduct(Integer id) throws DaoException;

    // QUERIES
    public List<Product> getAll() throws DaoException;

    public List<Product> getProductsByBrand(String brand) throws DaoException;

    public List<Product> getProductsByCategory(String category) throws DaoException;

    public List<Product> getProductsByPriceRange(Double min, Double max) throws DaoException;

    public List<Product> getProductsWithDiscount() throws DaoException;

    public List<Product> getProductsWithoutDiscount() throws DaoException;
}
