package ua.place.bet.auth.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.place.bet.auth.dao.UserDAO;
import ua.place.bet.lib.annotations.Inject;
import ua.place.bet.lib.annotations.Injector;
import ua.place.bet.model.models.User;

import java.util.Optional;

public class UserDAOMySQLImplTest {
    @Inject
    private static UserDAO userDAO;
    private static User user;

    @BeforeAll
    public static void initUserDAO() {
        userDAO = (UserDAO) Injector.getInstance(UserDAO.class);
        user = User.builder()
                .id(1L)
                .email("dima@gmail.com")
                .username("dima")
                .password("12345")
                .build();
    }

    @Test
    @Order(1)
    public void createUserShouldTrue() {
        Assertions.assertTrue(userDAO.createUser(user));
    }

    @Test
    @Order(2)
    public void readUserShouldById() {
        Optional<User> optionalUser = userDAO.readUser(user.getId());
        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(user, optionalUser.get());
    }

    @Test
    @Order(3)
    public void readUserShouldByEmailAndPassword() {
        Optional<User> optionalUser = userDAO.readUser(user.getEmail(), user.getPassword());
        Assertions.assertTrue(optionalUser.isPresent());
        Assertions.assertEquals(user, optionalUser.get());
    }
}