package expression.generic.calculators;

import expression.exceptions.CheckedAdd;
import expression.exceptions.CheckedMultiply;
import expression.exceptions.CheckedSubtract;
import expression.exceptions.calcexceptions.DivisionByZeroException;
import expression.exceptions.calcexceptions.OverflowException;

public class IntegerCalculator extends AbstractCalculator<Integer> {
    private final IntCalculationMode mode;

    public IntegerCalculator() {
        this(IntCalculationMode.NATIVE);
    }

    public IntegerCalculator(IntCalculationMode mode) {
        this.mode = mode;
    }

    @Override
    public Integer negate(Integer a) {
        if (mode != IntCalculationMode.NATIVE && a == Integer.MIN_VALUE) {
            return afterOverflow(true);
        }
        return -a;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        if (mode != IntCalculationMode.NATIVE) {
            if (CheckedAdd.checkOverflow(a, b)) {
                return afterOverflow(a >= 0);
            }
        }
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        if (mode != IntCalculationMode.NATIVE) {
            if (CheckedSubtract.checkOverflow(a, b)) {
                return afterOverflow(a >= 0);
            }
        }
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (mode != IntCalculationMode.NATIVE && CheckedMultiply.checkOverflow(a, b)) {
            return afterOverflow((a > 0 && b > 0) || (a < 0 && b < 0));
        }
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (b == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        if (mode != IntCalculationMode.NATIVE && a == Integer.MIN_VALUE && b == -1) {
            return afterOverflow(true);
        }
        return a / b;
    }

    private int afterOverflow(boolean upperBound) {
        if (mode == IntCalculationMode.CHECK_OVERFLOW) {
            throw new OverflowException("overflow");
        }
        return upperBound ? Integer.MAX_VALUE : Integer.MIN_VALUE;
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return Math.max(a, b);
    }

    @Override
    public Integer min(Integer a, Integer b) {
        return Math.min(a, b);
    }


    @Override
    public Integer count(Integer a) {
        return Integer.bitCount(a);
    }

    @Override
    public Integer parseConst(String str) {
        return Integer.valueOf(str);
    }

    @Override
    public Integer valueOf(int a) {
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntegerCalculator calculator) {
            return mode == calculator.mode;
        } else {
            return false;
        }
    }
}
