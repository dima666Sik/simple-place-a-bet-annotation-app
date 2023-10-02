package ua.place.bet.util.exceptions;

public class DBConnectionException extends Exception{
    public DBConnectionException() {}
    public DBConnectionException(String message) {
        super(message);
    }
    public DBConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    public DBConnectionException(Throwable cause) {
        super(cause);
    }
}
