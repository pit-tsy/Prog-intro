package expression.generic.parser;


import expression.generic.expressions.TripleExpression;
import expression.generic.calculators.Calculator;

@FunctionalInterface
public interface TripleParser<T> {
    TripleExpression<T> parse(Calculator<T> calculator, String expression);
}
