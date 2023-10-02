package ua.place.bet.auth.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ua.place.bet.auth.dao.UserDAO;
import ua.place.bet.auth.dao.query.hql.UserHQLQuery;
import ua.place.bet.lib.annotations.Component;
import ua.place.bet.model.models.User;
import ua.place.bet.util.DBConnector;

import java.util.Optional;
@Component
public class UserDAOImpl implements UserDAO {
    private final static Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    @Override
    public boolean createUser(User user) {
        return DBConnector.executeInsideTransactionIncludeRollback(session -> {
            session.persist(session.merge(user));
            logger.info("Create user was successful!");
            return true;
        });
    }

    @Override
    public Optional<User> readUser(long id) {
        return Optional.ofNullable(DBConnector.executeInsideTransactionWithoutRollback(session -> session.find(User.class, id)));
    }

    @Override
    public Optional<User> readUser(String email, String password) {
        return Optional.ofNullable(DBConnector.executeInsideTransactionWithoutRollback(session -> {
            Query<User> query = session.createQuery(UserHQLQuery.findUserByEmailAndPassword(), User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.uniqueResult();
        }));
    }
}
