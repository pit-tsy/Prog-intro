package expression.generic.expressions;

public class Max<T> extends BinaryOperation<T> implements CombineIntoOne {
    public Max(TripleExpression<T> exp1, TripleExpression<T> exp2) {
        super(exp1, exp2);
    }

    @Override
    protected T doOperation(T a, T b) {
        return calculator.max(a, b);
    }

    @Override
    protected boolean isDivisible() {
        return false;
    }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    public int getPriority() {
        return 400;
    }

    @Override
    public String getSign() {
        return "max";
    }
}
