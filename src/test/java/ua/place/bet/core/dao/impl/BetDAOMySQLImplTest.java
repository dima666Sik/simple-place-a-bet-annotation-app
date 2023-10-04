package ua.place.bet.core.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.place.bet.auth.dao.UserDAO;
import ua.place.bet.core.dao.BetDAO;
import ua.place.bet.lib.annotations.Inject;
import ua.place.bet.lib.annotations.Injector;
import ua.place.bet.model.models.Bet;
import ua.place.bet.model.models.User;

import java.util.List;

class BetDAOMySQLImplTest {
    private static final String USER_EMAIL = "dima@gmail.com";
    @Inject
    private static BetDAO betDAO;
    private static Bet bet1;
    private static Bet bet2;

    @BeforeAll
    public static void initUserDAO() {
        betDAO = (BetDAO) Injector.getInstance(BetDAO.class);
        User user = User.builder()
                .id(1L)
                .email(USER_EMAIL)
                .username("dima")
                .password("12345")
                .build();

        bet1 = Bet.builder()
                .id(1L)
                .money(100L)
                .coefficient(1.5)
                .user(user)
                .build();

        bet2 = Bet.builder()
                .id(2L)
                .money(120L)
                .coefficient(1.6)
                .user(user)
                .build();
    }

    @Test
    @Order(1)
    void createBet() {
        Assertions.assertTrue(betDAO.createBet(bet1));
    }

    @Test
    @Order(2)
    void readAllBetByUser() {
        Assertions.assertEquals(List.of(bet1, bet2), betDAO.readAllBetByUser(USER_EMAIL));
    }
}