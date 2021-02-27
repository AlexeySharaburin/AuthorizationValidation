package ru.netology.authorizationvalidation.repository;

import org.springframework.stereotype.Repository;
import ru.netology.authorizationvalidation.Authorities;
import ru.netology.authorizationvalidation.user.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class UserRepository {

    final Map<User, List<Authorities>> userAuthoritiesList = new ConcurrentHashMap<>();

    public List<Authorities> getUserAuthorities(User user) {

        for (Map.Entry<User, List<Authorities>> userAuthoritiesEntry : userAuthoritiesList.entrySet()) {

            if ((userAuthoritiesEntry.getKey().getUserName().equals(user.getUserName())) && (userAuthoritiesEntry.getKey().getUserPassword().equals(user.getUserPassword()))) {
                return userAuthoritiesEntry.getValue();
            }

        }

        return Collections.emptyList();
    }
}
