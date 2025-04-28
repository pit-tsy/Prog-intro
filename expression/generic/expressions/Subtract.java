package expression.generic.expressions;

public class Subtract<T> extends BinaryOperation<T> {
    public Subtract(TripleExpression<T> exp1, TripleExpression<T> exp2) {
        super(exp1, exp2);
    }

    @Override
    protected T doOperation(T a, T b) {
        return calculator.subtract(a, b);
    }

    @Override
    protected boolean isDivisible() {
        return true;
    }

    @Override
    protected boolean isCommutative() {
        return false;
    }

    @Override
    public int getPriority() {
        return 1600;
    }

    @Override
    public String getSign() {
        return "-";
    }
}
