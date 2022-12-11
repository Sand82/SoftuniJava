package com.example.mobilelele.model.validator;

import com.example.mobilelele.model.entities.UserEntity;
import com.example.mobilelele.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private UserService userService;

    public UniqueUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {

        if (userName == null) {

            return true;
        }

        return userService.getUserNameFree(userName);
    }
}
