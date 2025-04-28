package expression;

import java.math.BigDecimal;

import static java.lang.Math.max;

public class Max extends BinaryOperation implements CombineIntoOne {
    public Max(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        return max(a, b);
    }

    @Override
    protected BigDecimal doOperation(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("unsupported Bigdecimal operation");
    }

    @Override
    protected boolean isDivisible() {
        return false;
    }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    public int getPriority() {
        return 400;
    }

    @Override
    public String getSign() {
        return "max";
    }
}
