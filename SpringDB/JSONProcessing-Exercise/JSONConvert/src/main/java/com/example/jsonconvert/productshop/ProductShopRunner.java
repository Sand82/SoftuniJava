package com.example.jsonconvert.productshop;

import com.example.jsonconvert.productshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private SeedService seedService;

    @Autowired
    public ProductShopRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedProducts();
    }
}
