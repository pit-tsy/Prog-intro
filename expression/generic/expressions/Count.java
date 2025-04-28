package expression.generic.expressions;

public class Count<T> extends UnaryOperation<T> {

    public Count(TripleExpression<T> exp) {
        super(exp);
    }

    @Override
    public String getSign() {
        return "count";
    }

    @Override
    protected T doOperation(T x) {
        return calculator.count(x);
    }
}
