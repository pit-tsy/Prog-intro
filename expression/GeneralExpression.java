package expression;

public interface GeneralExpression extends Expression, BigDecimalExpression, TripleExpression {
    void toMiniString(StringBuilder builder, boolean brackets);

    void toString(StringBuilder builder);
}
