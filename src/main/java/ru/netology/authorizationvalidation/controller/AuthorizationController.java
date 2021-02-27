package ru.netology.authorizationvalidation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.authorizationvalidation.Authorities;
import ru.netology.authorizationvalidation.Users;
import ru.netology.authorizationvalidation.exception.InvalidCredentials;
import ru.netology.authorizationvalidation.exception.UnauthorizedUser;
import ru.netology.authorizationvalidation.service.AuthorizationService;
import ru.netology.authorizationvalidation.user.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid @Users(work = "Hallo") User user) {
        return service.getAuthorities(user);
    }
    @ExceptionHandler(InvalidCredentials.class)
    ResponseEntity<String> handle(InvalidCredentials e) {
        return new ResponseEntity<>("Error 400: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    ResponseEntity<String> handle(UnauthorizedUser e) {
        System.out.println("Error 401: " + e.getMessage());
        return new ResponseEntity<>("Error 401: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}

