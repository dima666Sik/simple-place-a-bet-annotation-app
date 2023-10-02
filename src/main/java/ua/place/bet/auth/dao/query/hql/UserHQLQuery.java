package ua.place.bet.auth.dao.query.hql;

public class UserHQLQuery {

    public static String findUserByEmailAndPassword() {
        return "FROM User u WHERE u.email = :email AND u.password = :password";
    }
}
