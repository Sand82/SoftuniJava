package com.example.jsonconvert.productshop.repositories;

import com.example.jsonconvert.productshop.entities.Product;
import com.example.jsonconvert.productshop.entities.products.ProductInRenegeExportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.example.jsonconvert.productshop.entities.products.ProductInRenegeExportDTO(" +
            "p.name, p.price, p.seller.firstName, p.seller.lastName) " +
            " FROM Product p " +
            " Where p.price > :firstBound AND p.price < :secondBound AND p.buyer IS NULL" +
            " ORDER BY p.price ASC")
    List<ProductInRenegeExportDTO> findByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal firstBound, BigDecimal secondBound);
}
