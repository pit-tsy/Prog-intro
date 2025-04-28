package expression.exceptions.parseexceptions;

public class NotExceptedSymbolException extends ParseException {
    public NotExceptedSymbolException(String message) {
        super(message);
    }
    public NotExceptedSymbolException(String message, int where) { super(message, where);}
}
