package expression.generic.calculators;

import expression.exceptions.calcexceptions.DivisionByZeroException;

public class ByteCalculator extends AbstractCalculator<Byte> {
    @Override
    public Byte negate(Byte a) {
        return (byte) -a;
    }

    @Override
    public Byte add(Byte a, Byte b) {
        return (byte) (a + b);
    }

    @Override
    public Byte subtract(Byte a, Byte b) {
        return (byte) (a - b);
    }

    @Override
    public Byte multiply(Byte a, Byte b) {
        return (byte) (a * b);
    }

    @Override
    public Byte divide(Byte a, Byte b) {
        if (b == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        return (byte) (a / b);
    }

    @Override
    public Byte max(Byte a, Byte b) {
        return (a > b ? a : b);
    }

    @Override
    public Byte min(Byte a, Byte b) {
        return (a < b ? a : b);
    }

    @Override
    public Byte count(Byte a) {
        return (byte) Integer.bitCount(a & 0xFF);
    }

    @Override
    public Byte parseConst(String str) {
        return Byte.parseByte(str);
    }

    @Override
    public Byte valueOf(int a) {
        return (byte) a;
    }
}
