package com.example.jsonconvert.productshop;

import com.example.jsonconvert.productshop.entities.User;
import com.example.jsonconvert.productshop.entities.products.ProductInRenegeExportDTO;
import com.example.jsonconvert.productshop.entities.users.UserWithSoldProductsExportDTO;
import com.example.jsonconvert.productshop.services.ProductService;
import com.example.jsonconvert.productshop.services.SeedService;
import com.example.jsonconvert.productshop.services.UserService;
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

    private UserService userService;

    private Gson gson;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //seedService.seedAll();

        //productsBetweenPriceWithoutBuyer(scanner);

        SuccessfullySoldProducts();
    }

    private void SuccessfullySoldProducts() {

        List<UserWithSoldProductsExportDTO> models = userService.successfullySoldProducts();

        String jsonModel = gson.toJson(models);

        System.out.println(jsonModel);
    }

    private void productsBetweenPriceWithoutBuyer(Scanner scanner) {

        List<ProductInRenegeExportDTO> models = productService.ProductsInRange(scanner);

        String jsonModel = gson.toJson(models);

        System.out.println(jsonModel);
    }
}
