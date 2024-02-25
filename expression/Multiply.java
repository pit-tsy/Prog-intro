package expression;

import java.math.BigDecimal;

public class Multiply extends BinaryOperation {
    public Multiply(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a * b;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    @Override
    protected boolean isDivisible() {
        return true;
    }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    public int getPriority() {
        return 2000;
    }

    @Override
    public String getSign() {
        return "*";
    }
}
