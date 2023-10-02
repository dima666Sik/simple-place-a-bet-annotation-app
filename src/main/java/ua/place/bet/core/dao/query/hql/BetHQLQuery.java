package ua.place.bet.core.dao.query.hql;

public class BetHQLQuery {
    public static String findBetsByUserEmail() {
        return "FROM Bet b WHERE b.user.email = :email";
    }
}
