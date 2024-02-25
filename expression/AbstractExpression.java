package expression;

public abstract class AbstractExpression implements GeneralExpression {
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
