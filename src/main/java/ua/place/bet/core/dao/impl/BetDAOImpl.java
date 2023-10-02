package ua.place.bet.core.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.place.bet.core.dao.BetDAO;
import ua.place.bet.core.dao.query.hql.BetHQLQuery;
import ua.place.bet.lib.annotations.Component;
import ua.place.bet.model.models.Bet;
import ua.place.bet.util.DBConnector;

import java.util.List;

@Component
public class BetDAOImpl implements BetDAO {
    private final static Logger logger = LogManager.getLogger(BetDAOImpl.class.getName());

    @Override
    public boolean createBet(Bet bet) {
        return DBConnector.executeInsideTransactionIncludeRollback(session -> {
            session.persist(session.merge(bet));
            logger.info("Create bet was successful!");
            return true;
        });
    }

    @Override
    public List<Bet> readAllBetByUser(String email) {
        return DBConnector.executeInsideTransactionWithoutRollback(session -> session.createQuery(BetHQLQuery.findBetsByUserEmail(), Bet.class).setParameter("email", email).getResultList());
    }
}
