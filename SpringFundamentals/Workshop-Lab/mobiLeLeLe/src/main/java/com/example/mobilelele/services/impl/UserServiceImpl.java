package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.model.entities.UserEntity;
import com.example.mobilelele.model.entities.UserRoleEntity;
import com.example.mobilelele.model.entities.enums.RoleEnum;
import com.example.mobilelele.model.services.UserLoginServiceModel;
import com.example.mobilelele.model.services.UserRegistrationServiceModel;
import com.example.mobilelele.repositories.UserRepository;
import com.example.mobilelele.repositories.UserRoleRepository;
import com.example.mobilelele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository,
                           ModelMapper mapper) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean isAuthenticate(String username, String password) {

        Optional<UserEntity> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {

            return false;
        }

        return passwordEncoder.matches(password, user.get().getPassword());
    }

    @Override
    public void loginUser(String userName) {

    }

    @Override
    public boolean getUserNameFree(String userName) {

        Optional<UserEntity> user = userRepository.findByUsername(userName);

        if (user != null) {
            return false;
        }

        return true;
    }

    @Override
    public void registerAndLoginUser(UserRegistrationBindingModel userRegistrationBindingModel) {

        UserRegistrationServiceModel userRegistrationServiceModel = mapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        UserRoleEntity userRole = userRoleRepository.findByRole(RoleEnum.USER.toString());

        UserEntity newUser = new UserEntity();

        newUser
                .setUsername(userRegistrationServiceModel.getUsername())
                .setFirstName(userRegistrationServiceModel.getFirstName())
                .setLastName(userRegistrationServiceModel.getLastName())
                .setActive(true)
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .setUserRoles(List.of(userRole));

        userRepository.save(newUser);

        //login(newUser);
    }

    @Override
    public UserEntity getByUsername(String name) {

        return userRepository.findByUsername(name).orElse(null);
    }

}
