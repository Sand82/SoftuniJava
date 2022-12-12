package com.example.mobilelele.services;

import com.example.mobilelele.model.binding.UserRegistrationBindingModel;
import com.example.mobilelele.model.services.UserLoginServiceModel;
import com.example.mobilelele.model.services.UserRegistrationServiceModel;

public interface UserService {

    boolean isAuthenticate(String userName, String password);

    void loginUser(String userName);

    boolean getUserNameFree(String userName);

    public boolean login(UserLoginServiceModel userLoginServiceModel);

    void registerAndLoginUser(UserRegistrationBindingModel model);
}
