package com.example.jsonconvert.productshop;

import com.example.jsonconvert.productshop.entities.products.ProductsInRangeDTO;
import com.example.jsonconvert.productshop.services.ProductService;
import com.example.jsonconvert.productshop.services.SeedService;
import com.example.jsonconvert.productshop.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.List;
import java.util.Scanner;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private SeedService seedService;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //seedService.seedProducts();

        productsInRange();
    }

    private void productsInRange() throws JAXBException {

        ProductsInRangeDTO models = productService.getInRange(500, 1000);

        JAXBContext context = JAXBContext.newInstance(ProductsInRangeDTO.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(models, System.out);
    }
}
