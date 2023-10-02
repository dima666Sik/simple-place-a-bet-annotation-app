package ua.place.bet.util;

import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.place.bet.util.exceptions.DBConnectionException;

public class DBConnectorTest {
    private static Session session;

    @BeforeAll
    public static void initConnection() throws DBConnectionException {
        session = DBConnector.getSession();
    }

    @Test
    @Order(1)
    public void testGetSession() {
        Assertions.assertTrue(session.isOpen());
    }

    @Test
    @Order(2)
    public void testCloseSessionFactory() {
        DBConnector.closeSessionFactory();
        Assertions.assertFalse(session.isOpen());
    }
}