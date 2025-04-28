package expression.exceptions.parseexceptions;

public class ParseException extends RuntimeException {
    private int position = -1;
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, int where) {
        super(message);
        this.position = where;
    }

    @Override
    public String getMessage() {
        if (position == -1) {
            return super.getMessage();
        } else {
            return "Position " + position + ": " + super.getMessage();
        }
    }
}
