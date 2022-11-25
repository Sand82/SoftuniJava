package com.example.jsonconvert.productshop.services.impl;

import com.example.jsonconvert.productshop.entities.Category;
import com.example.jsonconvert.productshop.entities.Product;
import com.example.jsonconvert.productshop.entities.User;
import com.example.jsonconvert.productshop.entities.category.CategoryImportDTO;
import com.example.jsonconvert.productshop.entities.products.ProductsImportDTO;
import com.example.jsonconvert.productshop.entities.users.UsersImportDTO;
import com.example.jsonconvert.productshop.repositories.CategoryRepository;
import com.example.jsonconvert.productshop.repositories.ProductRepository;
import com.example.jsonconvert.productshop.repositories.UserRepository;
import com.example.jsonconvert.productshop.services.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String USER_XML_PATH = "C:\\SoftUni\\SpringDB\\XMLProcessing-Exercise\\productShopXML\\src\\main\\resources\\productShop\\users.xml";
    private static final String PRODUCT_XML_PATH = "C:\\SoftUni\\SpringDB\\XMLProcessing-Exercise\\productShopXML\\src\\main\\resources\\productShop\\products.xml";
    private static final String CATEGORY_XML_PATH = "C:\\SoftUni\\SpringDB\\XMLProcessing-Exercise\\productShopXML\\src\\main\\resources\\productShop\\categories.xml";
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        CategoryImportDTO model = (CategoryImportDTO) unmarshaller.unmarshal(new FileReader(CATEGORY_XML_PATH));

        List<Category> categories = model.getCategories().stream().map(m -> new Category(m.getName())).collect(Collectors.toList());

        categoryRepository.saveAll(categories);
    }

    @Override
    public void seedUsers() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader reader = new FileReader(USER_XML_PATH);

        UsersImportDTO model = (UsersImportDTO) unmarshaller.unmarshal(reader);

        List<User> users = model.getUsers().stream().map(m -> mapper.map(m, User.class)).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader reader = new FileReader(PRODUCT_XML_PATH);

        ProductsImportDTO models = (ProductsImportDTO) unmarshaller.unmarshal(reader);

        List<Product> products = models
            .getProducts().stream()
            .map(m -> new Product(m.getName(), m.getPrice()))
            .map(this::setRandomCategories)
            .map(this::setRandomSeller)
            .map(this::setRandomBuyer)
            .collect(Collectors.toList());

        productRepository.saveAll(products);
    }


    private Product setRandomCategories(Product product) {

        Random random = new Random();
        long categoriesDbCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDbCount);

        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < count; i++) {

            int randomId = random.nextInt((int) categoriesDbCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());
        }

        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {

        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();

        product.setBuyer(buyer.get());

        return product;
    }

    private Optional<User> getRandomUser() {

        long userCount = this.userRepository.count();

        int randomUserId = new Random().nextInt((int) userCount) + 1;

        Optional<User> buyer = this.userRepository.findById(randomUserId);

        return buyer;
    }

    private Product setRandomSeller(Product product) {

        Optional<User> seller = getRandomUser();

        product.setSeller(seller.orElse(null));

        return product;
    }


}
