package ru.netology.authorizationvalidation.service;

import org.springframework.stereotype.Service;
import ru.netology.authorizationvalidation.Authorities;
import ru.netology.authorizationvalidation.exception.InvalidCredentials;
import ru.netology.authorizationvalidation.exception.UnauthorizedUser;
import ru.netology.authorizationvalidation.repository.UserRepository;
import ru.netology.authorizationvalidation.user.User;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(User user) {
        if (isEmpty(user.getUserName()) || isEmpty(user.getUserPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user.getUserName());
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}


