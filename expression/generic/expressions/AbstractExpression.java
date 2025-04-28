package expression.generic.expressions;

import expression.generic.calculators.Calculator;

public abstract class AbstractExpression<T> implements TripleExpression<T> {
    final protected Calculator<T> calculator;

    public AbstractExpression(Calculator<T> calculator) {
        this.calculator = calculator;
    }

    @Override
    public Calculator<T> getCalculator() {
        return calculator;
    }

    @Override
    public String toMiniString() {
        StringBuilder builder = new StringBuilder();
        toMiniString(builder, false);
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        toString(builder);
        return builder.toString();
    }
}
