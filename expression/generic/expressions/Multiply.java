package expression.generic.expressions;

public class Multiply<T> extends BinaryOperation<T> {
    public Multiply(TripleExpression<T> exp1, TripleExpression<T> exp2) {
        super(exp1, exp2);
    }

    @Override
    protected T doOperation(T a, T b) {
        return calculator.multiply(a, b);
    }

    @Override
    protected boolean isDivisible() {
        return true;
    }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    public int getPriority() {
        return 2000;
    }

    @Override
    public String getSign() {
        return "*";
    }
}
