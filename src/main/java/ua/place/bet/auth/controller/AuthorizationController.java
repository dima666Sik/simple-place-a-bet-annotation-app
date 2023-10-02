package ua.place.bet.auth.controller;

import lombok.RequiredArgsConstructor;
import ua.place.bet.auth.service.AuthorizationService;
import ua.place.bet.lib.annotations.Component;
import ua.place.bet.lib.annotations.Inject;
import ua.place.bet.model.dto.UserDTO;
import ua.place.bet.model.models.User;
import ua.place.bet.model.util.Mapper;

import java.util.Scanner;

import static ua.place.bet.auth.controller.util.UtilForNotification.printerNotification;
@Component
@RequiredArgsConstructor
public class AuthorizationController {
    private final Scanner scanner;
    @Inject
    private AuthorizationService authorizationService;

    public boolean registration() {
        printerNotification.accept("Enter your email: ");
        String email = scanner.nextLine();

        printerNotification.accept("Enter your username: ");
        String username = scanner.nextLine();

        printerNotification.accept("Enter your password: ");
        String password = scanner.nextLine();

        User user = User.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();

        return authorizationService.createUser(user);
    }

    public UserDTO authorization() {
        printerNotification.accept("Enter your email: ");
        String email = scanner.nextLine();

        printerNotification.accept("Enter your password: ");
        String password = scanner.nextLine();

        return Mapper.toDto(authorizationService.readUser(email, password).orElseThrow(() -> new RuntimeException("User is not founded!")));
    }
}
