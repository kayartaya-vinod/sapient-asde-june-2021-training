package com.sapient.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.sapient.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProductDao implements ProductDao {

    @Autowired
    EntityManager em;

    @Override
    public List<Product> getAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> getByBrand(String brand) {
        TypedQuery<Product> qry = em.createQuery("from Product where brand=?0", Product.class);
        qry.setParameter(0, brand);
        return qry.getResultList();
    }

    @Override
    public List<Product> getByCategory(String category) {
        TypedQuery<Product> qry = em.createQuery("from Product where category=?0", Product.class);
        qry.setParameter(0, category);
        return qry.getResultList();
    }

}
