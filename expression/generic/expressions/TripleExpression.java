package expression.generic.expressions;


import expression.exceptions.calcexceptions.CalculationException;
import expression.generic.calculators.Calculator;

public interface TripleExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z) throws CalculationException;
    Calculator<T> getCalculator();
    void toMiniString(StringBuilder builder, boolean brackets);
    void toString(StringBuilder builder);
}
