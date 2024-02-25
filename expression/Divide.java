package expression;

import java.math.BigDecimal;

public class Divide extends BinaryOperation {
    public Divide(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a / b;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    @Override
    protected boolean isDivisible() {
        return false;
    }

    @Override
    protected boolean isCommutative() {
        return false;
    }

    @Override
    public int getPriority() {
        return 2000;
    }

    @Override
    public String getSign() {
        return "/";
    }
}
