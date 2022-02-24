package epam.zlatamigas.xmltask.exception;

public class GemException extends Exception {

    public GemException() {
        super();
    }

    public GemException(String message) {
        super(message);
    }

    public GemException(Throwable cause) {
        super(cause);
    }

    public GemException(String message, Throwable cause) {
        super(message, cause);
    }
}
