package expression;

public interface GeneralExpression extends Expression, TripleExpression, ListExpression {
    void toMiniString(StringBuilder builder, boolean brackets);
    void toString(StringBuilder builder);
}
