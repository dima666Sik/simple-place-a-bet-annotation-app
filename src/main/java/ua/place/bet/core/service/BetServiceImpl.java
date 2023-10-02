package ua.place.bet.core.service;

import ua.place.bet.core.dao.BetDAO;
import ua.place.bet.lib.annotations.Component;
import ua.place.bet.lib.annotations.Inject;
import ua.place.bet.model.models.Bet;

import java.util.List;

@Component
public class BetServiceImpl implements BetService {
    @Inject
    private BetDAO betDAO;
    @Override
    public boolean createBet(Bet bet) {
        return betDAO.createBet(bet);
    }

    @Override
    public List<Bet> readAllBetByUser(String email) {
        return betDAO.readAllBetByUser(email);
    }
}
