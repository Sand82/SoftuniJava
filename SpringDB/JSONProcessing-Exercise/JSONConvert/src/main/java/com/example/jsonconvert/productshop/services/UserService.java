package com.example.jsonconvert.productshop.services;

import com.example.jsonconvert.productshop.entities.User;
import com.example.jsonconvert.productshop.entities.users.UserWithSoldProductsExportDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsExportDTO> successfullySoldProducts();
}
