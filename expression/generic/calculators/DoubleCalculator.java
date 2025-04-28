package expression.generic.calculators;

import expression.exceptions.calcexceptions.DivisionByZeroException;

public class DoubleCalculator extends AbstractCalculator<Double>{
    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double max(Double a, Double b) {
        return Math.max(a, b);
    }

    @Override
    public Double min(Double a, Double b) {
        return Math.min(a, b);
    }

    @Override
    public Double count(Double a) {
        long longBits = Double.doubleToLongBits(a);
        return (double) Long.bitCount(longBits);
    }

    @Override
    public Double parseConst(String str) {
        return Double.valueOf(str);
    }

    @Override
    public Double valueOf(int a) {
        return (double) a;
    }
}
