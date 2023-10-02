package ua.place.bet.model.util;

import ua.place.bet.model.dto.BetDTO;
import ua.place.bet.model.dto.UserDTO;
import ua.place.bet.model.models.Bet;
import ua.place.bet.model.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static User toUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
    }

    public static BetDTO toDto(Bet bet) {
        return BetDTO.builder()
                .money(bet.getMoney())
                .coefficient(bet.getCoefficient())
                .build();
    }

    public static Bet toBet(BetDTO betDTO) {
        return Bet.builder()
                .money(betDTO.getMoney())
                .coefficient(betDTO.getCoefficient())
                .build();
    }

    public static List<BetDTO> toListDTO(List<Bet> betList) {
        return betList.stream()
                .map(bet -> BetDTO.builder()
                        .money(bet.getMoney())
                        .coefficient(bet.getCoefficient())
                        .build())
                .collect(Collectors.toList());
    }
}