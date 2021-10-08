package com.sapient.dao;

import java.util.ResourceBundle;

public final class DaoFactory {
    private DaoFactory() {
    }

    // singleton
    private static ProductDao dao;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("factory");
        String productDaoImplClass = rb.getString("productDao.impl.class");
        try {
            dao = (ProductDao) Class.forName(productDaoImplClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProductDao getProductDao() {
        return dao;
    }
}
