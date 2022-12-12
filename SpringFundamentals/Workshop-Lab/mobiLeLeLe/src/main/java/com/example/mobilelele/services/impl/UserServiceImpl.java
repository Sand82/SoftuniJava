package com.example.mobilelele.services.impl;

import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.model.entities.UserEntity;
import com.example.mobilelele.model.entities.UserRoleEntity;
import com.example.mobilelele.model.entities.enums.RoleEnum;
import com.example.mobilelele.model.services.UserLoginServiceModel;
import com.example.mobilelele.model.services.UserRegistrationServiceModel;
import com.example.mobilelele.repositories.UserRepository;
import com.example.mobilelele.repositories.UserRoleRepository;
import com.example.mobilelele.security.CurrentUser;
import com.example.mobilelele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;
    private final UserRoleRepository userRoleRepository;

    private ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           CurrentUser currentUser,
                           UserRoleRepository userRoleRepository,
                           ModelMapper mapper) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
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

        currentUser.setAnonymous(false).setName(userName);
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
    public boolean login(UserLoginServiceModel loginServiceModel) {

        Optional<UserEntity> userEntityOpt =
                userRepository.findByUsername(loginServiceModel.getUsername());

        if (userEntityOpt.isEmpty()) {

            // logout();

            return false;
        } else {
            Boolean success = passwordEncoder.matches(
                    loginServiceModel.getPassword(), userEntityOpt.get().getPassword());

            if (success) {

                UserEntity loggedUser = userEntityOpt.get();

                login(loggedUser);
            }

            return success;
        }
    }

//    @Override
//    public void logout(){
//        currentUser.clean();
//    }

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

        login(newUser);
    }

    private void login(UserEntity user) {

        currentUser
                .setSetLoggedin(true)
                .setFirstName(user.getFirstName())
                .setUserName(user.getUsername())
                .setLastName(user.getLastName());
    }
}
