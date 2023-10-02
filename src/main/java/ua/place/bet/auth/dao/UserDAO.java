package ua.place.bet.auth.dao;

import ua.place.bet.model.models.User;

import java.util.Optional;

public interface UserDAO {
    boolean createUser(User user);
    Optional<User> readUser(long id);
    Optional<User> readUser(String email, String password);
}
