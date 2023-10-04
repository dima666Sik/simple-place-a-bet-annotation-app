package ua.place.bet.auth.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.place.bet.model.dto.UserDTO;
import ua.place.bet.model.models.User;
import ua.place.bet.model.util.Mapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

class AuthorizationControllerTest {
    private static AuthorizationController authorizationController;
    private static Scanner scanner;
    private static UserDTO userDTO;

    @BeforeAll
    public static void initUserDAO() {
        // user
        User user = User.builder()
                .id(1L)
                .email("dima@gmail.com")
                .username("dima")
                .password("12345")
                .build();
        userDTO = Mapper.toDto(user);
    }

    @Test
    void registrationShouldBeTrue() {
        String inputData = "dima@gmail.com\ndima\n12345\n";
        ByteArrayInputStream in = new ByteArrayInputStream(inputData.getBytes());
        // Origin input stream
        InputStream originalSystemIn = System.in;
        // Install your thread how standard thread input
        System.setIn(in);
        // Create Scanner to read data
        scanner = new Scanner(System.in);
        authorizationController = new AuthorizationController(scanner);
        scanner = new Scanner(originalSystemIn);
        scanner.close();
        Assertions.assertTrue(authorizationController.registration());
    }

    @Test
    void authorizationShouldGetUser() {
        String inputData = "dima@gmail.com\n12345\n";
        ByteArrayInputStream in = new ByteArrayInputStream(inputData.getBytes());
        // Origin input stream
        InputStream originalSystemIn = System.in;
        // Install your thread how standard thread input
        System.setIn(in);
        // Create Scanner to read data
        scanner = new Scanner(System.in);
        authorizationController = new AuthorizationController(scanner);
        scanner = new Scanner(originalSystemIn);
        scanner.close();
        Assertions.assertEquals(userDTO, authorizationController.authorization());
    }
}