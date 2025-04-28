package expression.generic.expressions;

import expression.generic.calculators.Calculator;

public class Variable<T> extends AbstractExpression<T> {
    private String var;

    public Variable(Calculator<T> calculator, String var) {
        super(calculator);
        this.var = var;
    }

    public Variable(Calculator<T> calculator, char var) {
        this(calculator, "" + var);
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Excepted : 'x', 'y' or 'z'. Got: " + var);
        };
    }

    @Override
    public void toMiniString(StringBuilder builder, boolean brackets) {
        builder.append(var);
    }

    @Override
    public void toString(StringBuilder builder) {
        builder.append(var);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable<?> variable) {
            return variable.var.equals(var);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }
}
