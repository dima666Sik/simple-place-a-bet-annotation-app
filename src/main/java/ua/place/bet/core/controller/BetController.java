package ua.place.bet.core.controller;

import lombok.RequiredArgsConstructor;
import ua.place.bet.core.service.BetService;
import ua.place.bet.lib.annotations.Component;
import ua.place.bet.lib.annotations.Inject;
import ua.place.bet.model.dto.BetDTO;
import ua.place.bet.model.dto.UserDTO;
import ua.place.bet.model.models.Bet;
import ua.place.bet.model.models.User;
import ua.place.bet.model.util.Mapper;

import java.util.List;
import java.util.Scanner;

import static ua.place.bet.auth.controller.util.UtilForNotification.printerNotification;
@Component
@RequiredArgsConstructor
public class BetController {
    private final Scanner scanner;
    @Inject
    private BetService betService;

    public boolean addBet(UserDTO userDTO){
        printerNotification.accept("Enter your bet money: ");
        Long money = Long.valueOf(scanner.nextLine());

        printerNotification.accept("Enter your bet coefficient: ");
        Double coefficient = Double.valueOf(scanner.nextLine());

        User user = Mapper.toUser(userDTO);
        Bet bet = Bet.builder().money(money).coefficient(coefficient).user(user).build();

        return betService.createBet(bet);
    }

    public List<BetDTO> getListBet(UserDTO userDTO){
        return Mapper.toListDTO(betService.readAllBetByUser(userDTO.getEmail()));
    }
}
