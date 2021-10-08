package com.sapient.programs;

import com.sapient.dao.DaoException;
import com.sapient.dao.ProductDao;
import com.sapient.dao.ProductDaoJpaImpl;
import com.sapient.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddProduct {
    public static void main(String[] args) {

        Product p = new Product();
        p.setName("Potato");
        p.setDescription("Organically grown potatoes");
        p.setQuantityPerUnit("500gm approximately 6 pcs");
        p.setUnitPrice(23.50);
        p.setDiscount(12.);
        p.setBrand("Malnad");
        p.setCategory("Vegetables");

        ProductDao dao = new ProductDaoJpaImpl(); // ideally get from factory
        try {
            dao.addProduct(p);
            log.debug("New product added successfully - {}", p);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
