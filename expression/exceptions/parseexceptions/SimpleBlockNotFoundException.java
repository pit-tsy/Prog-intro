package expression.exceptions.parseexceptions;

public class SimpleBlockNotFoundException extends ParseException {
    public SimpleBlockNotFoundException(String message) {
        super(message);
    }
    public SimpleBlockNotFoundException(String message, int where) { super(message, where);}
}
