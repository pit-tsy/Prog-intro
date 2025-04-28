package expression.generic.expressions;

import expression.generic.calculators.Calculator;

public class Const<T>  extends AbstractExpression<T> {
    private final T value;

    public Const(Calculator<T> calculator, T c) {
        super(calculator);
        this.value = c;
    }

    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public void toString(StringBuilder builder) {
        builder.append(value.toString());
    }

    @Override
    public void toMiniString(StringBuilder builder, boolean brackets) {
        builder.append(value.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const<?> c) {
            return c.value.equals(value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
