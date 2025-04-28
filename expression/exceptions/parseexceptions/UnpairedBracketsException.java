package expression.exceptions.parseexceptions;

public class UnpairedBracketsException extends ParseException {
    public UnpairedBracketsException(String message) {
        super(message);
    }

    public UnpairedBracketsException(String message, int where) {
        super(message, where);
    }

}

