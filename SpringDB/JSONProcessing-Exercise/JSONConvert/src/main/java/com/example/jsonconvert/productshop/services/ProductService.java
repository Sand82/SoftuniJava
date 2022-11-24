package com.example.jsonconvert.productshop.services;

import com.example.jsonconvert.productshop.entities.products.ProductInRenegeExportDTO;

import java.util.List;
import java.util.Scanner;

public interface ProductService {

    List<ProductInRenegeExportDTO> ProductsInRange(Scanner scanner);
}
