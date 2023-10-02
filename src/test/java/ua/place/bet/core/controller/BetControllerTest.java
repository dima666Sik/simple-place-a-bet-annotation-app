package ua.place.bet.core.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.place.bet.model.dto.BetDTO;
import ua.place.bet.model.dto.UserDTO;
import ua.place.bet.model.models.Bet;
import ua.place.bet.model.models.User;
import ua.place.bet.model.util.Mapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

class BetControllerTest {
    private BetController betController;
    private static User user;
    private static Bet bet;
    private static UserDTO userDTO;

    @BeforeAll
    public static void initUserDAO() {
        // user
        user = User.builder()
                .id(1L)
                .email("dima@gmail.com")
                .username("dima")
                .password("12345")
                .build();

        userDTO = Mapper.toDto(user);
    }

    @Test
    void addBetShouldBeTrue() {
        String inputData = "120\n1.6\n";
        ByteArrayInputStream in = new ByteArrayInputStream(inputData.getBytes());
        // Origin input stream
        InputStream originalSystemIn = System.in;
        // Install your thread how standard thread input
        System.setIn(in);
        // Create Scanner to read data
        Scanner scanner = new Scanner(System.in);
        betController = new BetController(scanner);
        scanner = new Scanner(originalSystemIn);
        scanner.close();
        Assertions.assertTrue(betController.addBet(userDTO));
    }

    @Test
    void getListBet() {
        BetDTO bet1 = BetDTO.builder()
                .money(100L)
                .coefficient(1.5)
                .build();
        BetDTO bet2 = BetDTO.builder()
                .money(120L)
                .coefficient(1.6)
                .build();
        Scanner scanner = new Scanner(System.in);
        betController = new BetController(scanner);
        Assertions.assertIterableEquals(List.of(bet1, bet2), betController.getListBet(userDTO));
    }
}