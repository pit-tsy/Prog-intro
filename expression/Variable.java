package expression;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Variable extends AbstractExpression {
    private String var;
    private int index = -1;

    public Variable(String var) {
        this.var = var;
    }

    public Variable(int index) {
        this("$" + index, index);
    }

    public Variable(String var, int index) {
        this.var = var;
        this.index = index;
    }

    public Variable(char var) {
        this.var = "" + var;
    }

    @Override
    public int evaluate(int x) {
        if (var.equals("x")) return x;
        throw new IllegalStateException("Excepted : x. Got: " + var);
    }

    @Override
    public int evaluate(int x, int y, int z) {
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
        if (obj instanceof Variable variable) {
            return variable.var.equals(var);
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(var);
    }

    @Override
    public int evaluate(List<Integer> variables) {
        if (index < 0 || index >= variables.size()) throw new IllegalStateException("Index out of variables");
        return variables.get(index);
    }
}
