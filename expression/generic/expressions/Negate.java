package expression.generic.expressions;

public class Negate<T> extends UnaryOperation<T> {
    public Negate(TripleExpression<T> exp) {
        super(exp);
    }

    @Override
    public String getSign() {
        return "-";
    }

    @Override
    protected T doOperation(T x) {
        return calculator.negate(x);
    }
}
