package expression;

import java.math.BigDecimal;

public class Const extends AbstractExpression {
    private final Number value;

    public Const(int c) {
        this.value = c;
    }

    public Const(BigDecimal c) {
        this.value = c;
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return (BigDecimal) value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
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
        if (obj instanceof Const c) {
            return c.value.equals(value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
