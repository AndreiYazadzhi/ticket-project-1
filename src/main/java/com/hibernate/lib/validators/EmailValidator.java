package com.hibernate.lib.validators;

import com.hibernate.lib.annotations.EmailValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private static final String VALID_EMAIL_REGEX = "\\w+@[a-z]+\\\\.[a-z]+";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches(VALID_EMAIL_REGEX) && (email.length() > 8);
    }
}
