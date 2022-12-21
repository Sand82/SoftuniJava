package com.example.mobilelele.services;

import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.model.services.UserLoginServiceModel;

public interface UserService {

    boolean isAuthenticate(String userName, String password);

    void loginUser(String userName);

    boolean getUserNameFree(String userName);



    void registerAndLoginUser(UserRegistrationBindingModel model);
}
