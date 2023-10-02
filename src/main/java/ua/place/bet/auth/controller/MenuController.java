package ua.place.bet.auth.controller;

import lombok.RequiredArgsConstructor;
import ua.place.bet.core.controller.BetController;
import ua.place.bet.lib.annotations.Component;
import ua.place.bet.lib.annotations.Inject;
import ua.place.bet.model.dto.UserDTO;
import ua.place.bet.model.models.User;
import ua.place.bet.model.util.Mapper;

import java.util.Scanner;

import static ua.place.bet.auth.controller.util.UtilForNotification.printerNewLineNotification;
import static ua.place.bet.auth.controller.util.UtilForNotification.printerNotification;
@Component
@RequiredArgsConstructor
public class MenuController {
    private final Scanner scanner;
    @Inject
    private AuthorizationController authorizationController;
    @Inject
    private BetController betController;
    private UserDTO userDTO;

    public void menuControl() {
        OUTER:
        while (true) {
            printerNewLineNotification.accept("This is menu program, make your choice.");
            printerNewLineNotification.accept("Enter `run` to start work.");
            printerNewLineNotification.accept("Enter `end` to end work.");
            String mainChoice = scanner.nextLine();
            switch (mainChoice) {
                case "run": {
                    printerNewLineNotification.accept("U enter run... So program is run work...");
                    runController();
                    break;
                }
                case "end": {
                    printerNewLineNotification.accept("U enter end... So program is end work...");
                    break OUTER;
                }
                default: {
                    printerNewLineNotification.accept("U enter wrong command...");
                }
            }
        }
    }

    private void runController() {
        INNER:
        while (true) {
            printerNewLineNotification.accept("\nThis is menu program, make your choice.");
            printerNewLineNotification.accept("Enter `reg` to registration user.");
            printerNewLineNotification.accept("Enter `auth` to authorization user.");
            printerNewLineNotification.accept("Enter `back` to back on main menu.");
            String mainChoice = scanner.nextLine();
            switch (mainChoice) {
                case "reg": {
                    printerNewLineNotification.accept("U enter reg...");
                    System.out.println(authorizationController);
                    if (authorizationController.registration()) {
                        printerNewLineNotification.accept("The `Auth` was successful.");
                    } else {
                        printerNewLineNotification.accept("The `Auth` was not successful.");
                    }
                    break;
                }
                case "auth": {
                    printerNewLineNotification.accept("U enter auth...");
                    userDTO = authorizationController.authorization();
                    runBetController();
                    break;
                }
                case "back": {
                    printerNewLineNotification.accept("U back into main menu...");
                    break INNER;
                }
                default: {
                    printerNewLineNotification.accept("U enter wrong command...");
                }
            }
        }
    }

    private void runBetController() {
        SUPER_INNER:
        while (true) {
            printerNewLineNotification.accept("\nThis is menu program, make your choice.");
            printerNewLineNotification.accept("Enter `bet` to registration user.");
            printerNewLineNotification.accept("Enter `list-bet` to authorization user.");
            printerNewLineNotification.accept("Enter `back` to back on menu (auth/reg).");
            String mainChoice = scanner.nextLine();
            switch (mainChoice) {
                case "bet": {
                    printerNewLineNotification.accept("I want to place a bet...");
                    betController.addBet(userDTO);
                    break;
                }
                case "list-bet": {
                    printerNewLineNotification.accept("I want to get a list of bet...");
                    betController.getListBet(userDTO).forEach(System.out::println);
                    break;
                }
                case "back": {
                    printerNewLineNotification.accept("U back into main menu...");
                    break SUPER_INNER;
                }
                default: {
                    printerNewLineNotification.accept("U enter wrong command...");
                }
            }
        }
    }
}
