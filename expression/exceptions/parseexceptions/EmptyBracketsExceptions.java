package expression.exceptions.parseexceptions;

public class EmptyBracketsExceptions extends ParseException {

    public EmptyBracketsExceptions(String message) {
        super(message);
    }

    public EmptyBracketsExceptions(String message, int where) {super(message, where);}
}
