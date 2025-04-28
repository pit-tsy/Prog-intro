package expression.exceptions.parseexceptions;

public class ExpectedEndOfExpressionException extends ParseException {
    public ExpectedEndOfExpressionException(String message) {
        super(message);
    }

    public ExpectedEndOfExpressionException(String message, int where) {
        super(message, where);
    }
}
