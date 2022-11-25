package com.example.jsonconvert.productshop.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SeedService {

    void seedUsers() throws FileNotFoundException, JAXBException;

    void seedCategories() throws FileNotFoundException, JAXBException;

    void seedProducts() throws FileNotFoundException;

    default void seedAll() throws FileNotFoundException, JAXBException {
        seedUsers();
        seedCategories();
        seedProducts();

    }
}
