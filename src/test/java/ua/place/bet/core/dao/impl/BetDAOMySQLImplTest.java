package ua.place.bet.core.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.place.bet.core.dao.BetDAO;
import ua.place.bet.model.models.Bet;
import ua.place.bet.model.models.User;

import java.util.List;

class BetDAOMySQLImplTest {
    private static final String USER_EMAIL = "dima@gmail.com";
    private static BetDAO betDAO;
    private static Bet bet;
    private static User user;

    @BeforeAll
    public static void initUserDAO() {
        betDAO = new BetDAOImpl();
        user = User.builder()
                .id(1L)
                .email(USER_EMAIL)
                .username("dima")
                .password("12345")
                .build();

        bet = Bet.builder()
                .id(1L)
                .money(100L)
                .coefficient(1.5)
                .user(user)
                .build();
    }

    @Test
    @Order(1)
    void createBet() {
        Assertions.assertTrue(betDAO.createBet(bet));
    }

    @Test
    @Order(2)
    void readAllBetByUser() {
        Assertions.assertEquals(List.of(bet), betDAO.readAllBetByUser(USER_EMAIL));
    }
}