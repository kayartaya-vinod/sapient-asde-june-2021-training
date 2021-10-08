package com.sapient.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.sapient.entity.Product;

public class ProductDaoJpaImpl implements ProductDao {

    private EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TRAINING");
        return emf.createEntityManager();
    }

    @Override
    public List<Product> getAllProducts() throws DaoException {
        EntityManager em = this.getEntityManager();
        TypedQuery<Product> qry = em.createQuery("from Product", Product.class);
        List<Product> list = qry.getResultList();
        em.close();
        return list;
    }

    @Override
    public Product getById(Integer id) throws DaoException {
        EntityManager em = this.getEntityManager();
        Product p = em.find(Product.class, id);
        em.close();
        return p;
    }

    @Override
    public List<Product> getProductsByPriceRange(Double min, Double max) {
        EntityManager em = this.getEntityManager();
        TypedQuery<Product> qry = em.createQuery("from Product where unitPrice between ?0 and ?1", Product.class);
        qry.setParameter(0, min);
        qry.setParameter(1, max);
        List<Product> list = qry.getResultList();
        em.close();
        return list;
    }

}
