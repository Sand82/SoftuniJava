package com.example.jsonconvert.productshop;

import com.example.jsonconvert.productshop.entities.products.ProductInRenegeExportDTO;
import com.example.jsonconvert.productshop.services.ProductService;
import com.example.jsonconvert.productshop.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private SeedService seedService;
    private ProductService productService;

    private Gson gson;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService) {
        this.seedService = seedService;
        this.productService = productService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
        //seedService.seedProducts();

        Scanner scanner = new Scanner(System.in);

        List<ProductInRenegeExportDTO> models = productService.ProductsInRange(scanner);

        String jsonModel = gson.toJson(models);

        System.out.println(jsonModel);
    }
}
