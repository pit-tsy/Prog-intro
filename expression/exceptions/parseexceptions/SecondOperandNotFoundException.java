package expression.exceptions.parseexceptions;

public class SecondOperandNotFoundException extends ParseException {

    public SecondOperandNotFoundException(String message) {
        super(message);
    }
    public SecondOperandNotFoundException(String message, int where) { super(message, where);}
}
