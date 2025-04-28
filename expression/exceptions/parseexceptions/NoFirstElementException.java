package expression.exceptions.parseexceptions;

public class NoFirstElementException extends ParseException {
    public NoFirstElementException(String message) {
        super(message);
    }
    public NoFirstElementException(String message, int where) { super(message, where);}
}
