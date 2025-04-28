package expression.generic.expressions;

public class Divide<T> extends BinaryOperation<T> {
    public Divide(TripleExpression<T> exp1, TripleExpression<T> exp2) {
        super(exp1, exp2);
    }

    @Override
    protected T doOperation(T a, T b) {
        return calculator.divide(a, b);
    }

    @Override
    protected boolean isDivisible() {
        return false;
    }

    @Override
    protected boolean isCommutative() {
        return false;
    }

    @Override
    public int getPriority() {
        return 2000;
    }

    @Override
    public String getSign() {
        return "/";
    }
}
