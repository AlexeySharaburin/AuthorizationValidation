package ru.netology.authorizationvalidation.user;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
public class User {

    @NotBlank(message = "Name cannot be null")
    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Min(value = 3, message = "Password is short")
    @Max(value = 10, message = "Password is long")
    private String userPassword;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

