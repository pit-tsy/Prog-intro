package expression;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Const  extends AbstractExpression {
    private final Number value;

    public Const(int c) {
        this.value = c;
    }

    public Const(BigDecimal c) { this.value = c; }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(List<Integer> variables) {
        return value.intValue();
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
