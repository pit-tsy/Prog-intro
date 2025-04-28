package expression.exceptions.parseexceptions;

public class InvalidBinSignException extends ParseException {
    public InvalidBinSignException(String message) {
        super(message);
    }
    public InvalidBinSignException(String message, int where) { super(message, where);}
}
