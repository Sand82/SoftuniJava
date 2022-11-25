package com.example.jsonconvert.productshop.services.impl;

import com.example.jsonconvert.productshop.repositories.ProductRepository;
import com.example.jsonconvert.productshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
