package com.example.coffeeshop.services.impl;

import com.example.coffeeshop.models.bindings.UserRegisterBindingModel;
import com.example.coffeeshop.models.entities.User;
import com.example.coffeeshop.models.views.UserOrdersViewModel;
import com.example.coffeeshop.repositories.UserRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper mapper;
    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
    }

    @Override
    public void createUser(UserRegisterBindingModel userRegisterBindingModel) {

        User user = mapper.map(userRegisterBindingModel, User.class);

        userRepository.save(user);
    }

    @Override
    public boolean userExist(String username, String password) {

        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        if (user.isEmpty()) {

            return false;
        }

        setCurrentUser(user.orElse(null));

        return true;
    }

    @Override
    public void logout(HttpSession httpSession) {

        httpSession.invalidate();
    }

    @Override
    public User getById(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    public List<UserOrdersViewModel> getAllModels() {

        List<User> users = userRepository.findAll();

        List<UserOrdersViewModel> models = users.stream()
                .filter(u -> u.getOrders().size() != 0)
                .map(u -> {
                    UserOrdersViewModel model = new UserOrdersViewModel();
                    model.setUsername(u.getUsername());
                    model.setCountOfOrders(u.getOrders().size());
                    return model;

                })
                .sorted(Comparator.comparing(UserOrdersViewModel::getCountOfOrders)
                .reversed()).toList();

        return models;
    }

    private void setCurrentUser(User user) {

        long id = user.getId();
        String username = user.getUsername();

        currentUser.setId(id);
        currentUser.setUsername(username);
    }
}
