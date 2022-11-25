package com.example.jsonconvert.productshop.services.impl;

import com.example.jsonconvert.productshop.entities.User;
import com.example.jsonconvert.productshop.entities.users.UserWithSoldProductsExportDTO;
import com.example.jsonconvert.productshop.repositories.UserRepository;
import com.example.jsonconvert.productshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsExportDTO> successfullySoldProducts() {

        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<UserWithSoldProductsExportDTO> model = users.stream()
                .map(u -> mapper.map(u, UserWithSoldProductsExportDTO.class))
                .collect(Collectors.toList());

        return model;
    }
}
