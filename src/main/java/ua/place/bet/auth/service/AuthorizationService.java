package ua.place.bet.auth.service;

import ua.place.bet.model.models.User;

import java.util.Optional;

public interface AuthorizationService {
    boolean createUser(User user);
    Optional<User> readUser(long id);
    Optional<User> readUser(String email, String password);
}
