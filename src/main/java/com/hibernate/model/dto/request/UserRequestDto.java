package com.hibernate.model.dto.request;

import com.hibernate.lib.annotations.EmailValidation;
import com.hibernate.lib.annotations.PasswordValidation;
import javax.validation.constraints.Min;

@PasswordValidation(message = "Passwords are not equal")
public class UserRequestDto {
    @EmailValidation
    private String email;
    @Min(6)
    private String password;
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
