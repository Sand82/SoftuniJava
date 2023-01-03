package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.model.entities.UserEntity;
import com.example.mobilelele.model.entities.UserRoleEntity;
import com.example.mobilelele.model.entities.enums.RoleEnum;
import com.example.mobilelele.model.services.UserRegistrationServiceModel;
import com.example.mobilelele.repositories.UserRepository;
import com.example.mobilelele.repositories.UserRoleRepository;
import com.example.mobilelele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper mapper;
    private final MobileleUserServiceImpl mobileleUserService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserRoleRepository userRoleRepository,
                           ModelMapper mapper,
                           MobileleUserServiceImpl mobileleUserService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.mapper = mapper;
        this.mobileleUserService = mobileleUserService;
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

        UserRoleEntity userRole = userRoleRepository.findByRole(RoleEnum.USER);

        UserEntity newUser = new UserEntity();

        newUser
                .setUsername(userRegistrationServiceModel.getUsername())
                .setFirstName(userRegistrationServiceModel.getFirstName())
                .setLastName(userRegistrationServiceModel.getLastName())
                .setActive(true)
                .setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword()))
                .setUserRoles(List.of(userRole));

//        UserDetails principal = mobileleUserService.loadUserByUsername(newUser.getUsername());
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                principal,
//                newUser.getPassword(),
//                principal.getAuthorities()
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        userRepository.save(newUser);
    }

    @Override
    public UserEntity getByUsername(String name) {

        return userRepository.findByUsername(name).orElse(null);
    }
}
