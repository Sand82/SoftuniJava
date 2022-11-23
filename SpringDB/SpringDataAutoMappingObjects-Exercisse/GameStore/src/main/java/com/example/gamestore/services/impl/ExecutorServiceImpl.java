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

    public String execute(String commandLine) {

        String[] commandParts = commandLine.split("\\|");

        String commandName = commandParts[0];

        switch (commandName) {
            case REGISTER_USER_COMMAND:

                return registerUser(commandParts);
            case LOGIN_USER_COMMAND:

                return loginUser(commandParts);

            case LOGOUT_USER_COMMAND:

              return   userService.logout();

            case "End":
              return "End";
            default:
                return "Wrong command type";
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
    }



    private String loginUser(String[] commandParts) {
        String result;

        LoginDTO loginData = new LoginDTO(commandParts);
        User user = userService.login(loginData);

        if (user == null) {
            result = "This user don't exist.";
        }else {

            result = String.format("Successfully logged %s.", user.getFullName());
        }
        return result;
    }

    private String registerUser(String[] commandParts) {

        RegisterDTO registerData = new RegisterDTO(commandParts);
        User user = userService.register(registerData);

        return String.format("%s was registered.", user.getFullName());
    }
}
