package expression;

import java.math.BigDecimal;

public abstract class UnaryOperation extends AbstractExpression implements Operation {
    GeneralExpression exp;

    public UnaryOperation(GeneralExpression exp) {
        this.exp = exp;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return doOperation(exp.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return doOperation(exp.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return doOperation(exp.evaluate(x, y, z));
    }

    protected abstract int doOperation(int x);

    protected abstract BigDecimal doOperation(BigDecimal x);

    @Override
    public void toMiniString(StringBuilder builder, boolean brackets) {
        if (brackets) builder.append('(');
        builder.append(getSign());
        if (needBrackets()) {
            exp.toMiniString(builder, true);
        } else {
            builder.append(' ');
            exp.toMiniString(builder, false);
        }
        if (brackets) builder.append(')');
    }

    @Override
    public void toString(StringBuilder builder) {
        builder.append(getSign());
        builder.append('(');
        exp.toString(builder);
        builder.append(')');
    }

    private boolean needBrackets() {
        return exp instanceof BinaryOperation;
    }

    @Override
    public int getPriority() {
        return 10000;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnaryOperation operation) {
            return getClass().equals(operation.getClass())
                    && operation.exp.equals(exp);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 32 * exp.hashCode() + getSign().hashCode();
    }
}
