package ua.place.bet.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.place.bet.model.models.Bet;
import ua.place.bet.model.models.User;
import ua.place.bet.util.exceptions.DBConnectionException;

import java.util.function.Consumer;
import java.util.function.Function;

public class DBConnector {
    private final static Logger logger = LogManager.getLogger(DBConnector.class.getName());
    private final static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Bet.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() throws DBConnectionException {
        try {
            logger.info("Get session was successful!");
            return sessionFactory.openSession();
        } catch (Exception e) {
            logger.error(e);
            throw new DBConnectionException("Get session was not successful!", e);
        }
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

    public static <T> T executeInsideTransactionIncludeRollback(Function<Session, T> sessionAction) {
        T result = null;
        try (Session session = getSession()) {
            session.beginTransaction();
            try {
                result = sessionAction.apply(session);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                logger.error(e);
                throw e;
            }
        } catch (DBConnectionException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <T> T executeInsideTransactionWithoutRollback(Function<Session, T> sessionAction) {
        T result = null;
        try (Session session = getSession()) {
            session.beginTransaction();
            result = sessionAction.apply(session);
            session.getTransaction().commit();
        } catch (DBConnectionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
