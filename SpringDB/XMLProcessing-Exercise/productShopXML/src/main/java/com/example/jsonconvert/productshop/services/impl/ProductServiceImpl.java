package com.example.jsonconvert.productshop.services.impl;

import com.example.jsonconvert.productshop.entities.category.CategoriesProductCountExportDTO;
import com.example.jsonconvert.productshop.entities.products.ProductInRenegeExportDTO;
import com.example.jsonconvert.productshop.repositories.ProductRepository;
import com.example.jsonconvert.productshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductInRenegeExportDTO> ProductsInRange(Scanner scanner) {
        int firstBound = Integer.parseInt(scanner.nextLine());
        int secondBound = Integer.parseInt(scanner.nextLine());

        List<ProductInRenegeExportDTO> products = productRepository.findByPriceBetweenAndBuyerIsNullOrderByPriceAsc(
                BigDecimal.valueOf(firstBound),BigDecimal.valueOf(secondBound));

        return products;
    }

    @Override
    public List<CategoriesProductCountExportDTO> getCategoryStatistics() {

        List<CategoriesProductCountExportDTO> model = productRepository.getCategoryStats();

        return model;
    }
}
