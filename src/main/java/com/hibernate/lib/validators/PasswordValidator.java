package com.hibernate.lib.validators;

import com.hibernate.lib.annotations.PasswordValidation;
import com.hibernate.model.dto.request.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordValidation, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        UserRequestDto user = (UserRequestDto) value;
        if(user.getPassword() == null
                || user.getRepeatPassword() == null) {
            return false;
        }
        return user.getPassword().equals(user.getRepeatPassword());
    }
}
