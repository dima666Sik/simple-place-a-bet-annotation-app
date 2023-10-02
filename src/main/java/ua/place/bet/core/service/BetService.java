package ua.place.bet.core.service;

import ua.place.bet.model.models.Bet;

import java.util.List;

public interface BetService {
    boolean createBet(Bet bet);
    List<Bet> readAllBetByUser(String email);
}
