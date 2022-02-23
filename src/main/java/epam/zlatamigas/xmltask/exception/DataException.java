package epam.zlatamigas.xmltask.exception;

public class DataException extends Exception {

    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(Throwable cause) {
        super(cause);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
