package com.au.spring_jpa.validator;

import com.au.spring_jpa.dto.UserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<ValidUserType, UserDto> {
    @Override
    public boolean isValid(UserDto dto, ConstraintValidatorContext ctx) {
//        if (dto instanceof AdminUserDto admin && (admin.getPermissions() == null || admin.getPermissions().isBlank())) {
//            return false;
//        }
//        if (dto instanceof CustomerUserDto cust && cust.getLoyaltyPoints() == null) {
//            return false;
//        }
        return true;
    }
}