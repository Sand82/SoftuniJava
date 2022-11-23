package com.example.gamestore.services.impl;

import com.example.gamestore.entities.User;
import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegisterDTO;
import com.example.gamestore.services.ExecutorService;
import com.example.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutorServiceImpl implements ExecutorService {

    private UserService userService;

    @Autowired
    public ExecutorServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public String execute(String commandLine){

        String[] commandParts = commandLine.split("\\|");

        String commandName = commandParts[0];

        String result = "";

        User user = null;

        switch (commandName){
            case REGISTER_USER_COMMAND:

                RegisterDTO registerData = new RegisterDTO(commandParts);
                user = userService.register(registerData);

                result = String.format("%s was registered.", user.getFullName());
                break;
            case LOGIN_USER_COMMAND:

                LoginDTO loginData = new LoginDTO(commandParts);
                user = userService.login(loginData);

                result = String.format("Successfully logged %s.", user.getFullName());
                break;
            case LOGOUT_USER_COMMAND:
                userService.logout();
                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
//            case "":
//                break;
        }

        return result;
    }

}
