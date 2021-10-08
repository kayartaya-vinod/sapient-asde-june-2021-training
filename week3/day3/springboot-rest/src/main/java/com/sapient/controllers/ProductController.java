package com.sapient.controllers;

import java.util.List;

import com.sapient.dao.ProductDao;
import com.sapient.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductDao dao;

    @GetMapping("/info")
    public String info() {
        return "ProductController exposed via /api/products";
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable Integer id) {
        return dao.findById(id).get();
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product) {
        return dao.save(product);
    }

    @GetMapping("/by/brand/{brand}")
    public List<Product> getByBrand(@PathVariable String brand) {
        return dao.findByBrand(brand);
    }

    @GetMapping("/between/inr/{min}/{max}")
    public List<Product> getByPriceRange(@PathVariable Double min, @PathVariable Double max) {
        return dao.findByPriceRange(min, max);
    }

    @GetMapping
    public Iterable<Product> getAll(@RequestParam(name = "_page", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(name = "_limit", required = false, defaultValue = "10") Integer pageLimit) {

        // Pageable pgbl = Pageable.ofSize(pageLimit)
        Pageable pgbl = PageRequest.of(pageNo - 1, pageLimit);
        return dao.findAll(pgbl);
    }

}
