package expression.exceptions.parseexceptions;

public class IntOverflowException extends ParseException {

    public IntOverflowException(String message) {
        super(message);
    }
    public IntOverflowException(String message, int where) { super(message, where);}
}
