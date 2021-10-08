package com.sapient.web.controllers;

import java.util.List;

import com.sapient.dao.ProductDao;
import com.sapient.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // a type of @Component used in the web layer
public class ProductsController {

    @Autowired
    ProductDao dao;

    @RequestMapping(path = { "/show-products", "/products" })
    public String getAllProducts(Model model) {
        model.addAttribute("title", "List of products");
        model.addAttribute("products", dao.getAll());
        return "show-products"; // view name --> resolved to view filename by InternalResourceViewResolver
    }

    // http://example.com/product-details?id=11
    @RequestMapping("/product-details")
    public String getById(@RequestParam Integer id, Model model) {
        model.addAttribute("title", "Details of product with id: " + id);
        model.addAttribute("prd", dao.getById(id));
        return "product-details";
    }

    // http://example.com/products/by/brand/Zespri
    // http://example.com/products/by/brand/Malnad
    // http://example.com/products/by/brand/Fresho
    @RequestMapping(method = RequestMethod.GET, path = "/products/by/brand/{brand}")
    public String getByBrand(@PathVariable String brand, Model model) {
        model.addAttribute("title", "Products by brand: " + brand);
        model.addAttribute("products", dao.getByBrand(brand));
        return "show-products";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "/api/products", produces = "application/json")
    public List<Product> getProductsAsJson() {
        return dao.getAll();
    }

}
