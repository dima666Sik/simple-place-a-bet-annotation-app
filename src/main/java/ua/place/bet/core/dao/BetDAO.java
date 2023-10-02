package ua.place.bet.core.dao;

import ua.place.bet.model.models.Bet;
import ua.place.bet.model.models.User;

import java.util.List;
import java.util.Optional;

public interface BetDAO {
    boolean createBet(Bet bet);
    List<Bet> readAllBetByUser(String email);
}
