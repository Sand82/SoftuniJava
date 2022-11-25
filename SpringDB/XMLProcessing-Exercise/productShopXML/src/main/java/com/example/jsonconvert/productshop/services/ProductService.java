package com.example.jsonconvert.productshop.services;

import com.example.jsonconvert.productshop.entities.products.ProductsInRangeDTO;

public interface ProductService {

    ProductsInRangeDTO getInRange(int lowerBound, int higherBound);
}
