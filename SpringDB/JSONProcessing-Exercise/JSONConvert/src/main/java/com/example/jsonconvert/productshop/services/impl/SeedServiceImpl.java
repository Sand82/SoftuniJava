package com.example.jsonconvert.productshop.services.impl;

import com.example.jsonconvert.productshop.entities.Category;
import com.example.jsonconvert.productshop.entities.Product;
import com.example.jsonconvert.productshop.entities.User;
import com.example.jsonconvert.productshop.entities.category.CategoryImportDTO;
import com.example.jsonconvert.productshop.entities.products.ProductImportDTO;
import com.example.jsonconvert.productshop.entities.users.UserImportDTO;
import com.example.jsonconvert.productshop.repositories.CategoryRepository;
import com.example.jsonconvert.productshop.repositories.ProductRepository;
import com.example.jsonconvert.productshop.repositories.UserRepository;
import com.example.jsonconvert.productshop.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String USER_JSON_PATH = "src/main/resources/productShop/users.json";
    private static final String PRODUCT_JSON_PATH = "src/main/resources/productShop/products.json";
    private static final String CATEGORY_JSON_PATH = "src/main/resources/productShop/categories.json";
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,  ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {

        FileReader fileReader = new FileReader(USER_JSON_PATH);

        UserImportDTO[] userImportDTOS = this.gson.fromJson(fileReader, UserImportDTO[].class);

        List<User> users = Arrays.stream(userImportDTOS)
                .map(dto -> this.mapper.map(dto, User.class))
                .collect(Collectors.toList());

        this.userRepository.saveAll(users);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCT_JSON_PATH);

        ProductImportDTO[]  ProductImportDTOS = this.gson.fromJson(fileReader,  ProductImportDTO[].class);

        List<Product> products = Arrays.stream(ProductImportDTOS)
                .map(dto -> this.mapper.map(dto, Product.class))
                .collect(Collectors.toList());

        this.productRepository.saveAll(products);
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader fileReader = new FileReader(CATEGORY_JSON_PATH);

        CategoryImportDTO[]  CategoryImportDTOS = this.gson.fromJson(fileReader,  CategoryImportDTO[].class);

        List<Category> category = Arrays.stream(CategoryImportDTOS)
                .map(dto -> this.mapper.map(dto, Category.class))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(category);
    }
}
