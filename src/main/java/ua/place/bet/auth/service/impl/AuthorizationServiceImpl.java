package ua.place.bet.auth.service.impl;

import ua.place.bet.auth.dao.UserDAO;
import ua.place.bet.auth.service.AuthorizationService;
import ua.place.bet.lib.annotations.Component;
import ua.place.bet.lib.annotations.Inject;
import ua.place.bet.model.models.User;

import java.util.Optional;
@Component
public class AuthorizationServiceImpl implements AuthorizationService {
    @Inject
    private UserDAO userDAO;
    @Override
    public boolean createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public Optional<User> readUser(long id) {
        return userDAO.readUser(id);
    }

    @Override
    public Optional<User> readUser(String email, String password) {
        return userDAO.readUser(email, password);
    }
}
