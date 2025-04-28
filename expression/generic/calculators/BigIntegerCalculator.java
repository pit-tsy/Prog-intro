package expression.generic.calculators;

import expression.exceptions.calcexceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerCalculator extends AbstractCalculator<BigInteger> {
    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException("division by zero");
        }
        return a.divide(b);
    }

    @Override
    public BigInteger max(BigInteger a, BigInteger b) {
        return a.max(b);
    }

    @Override
    public BigInteger min(BigInteger a, BigInteger b) {
        return a.min(b);
    }

    @Override
    public BigInteger count(BigInteger a) {
        return BigInteger.valueOf(a.bitCount());
    }

    @Override
    public BigInteger parseConst(String str) {
        return new BigInteger(str);
    }

    @Override
    public BigInteger valueOf(int a) {
        return BigInteger.valueOf(a);
    }
}
