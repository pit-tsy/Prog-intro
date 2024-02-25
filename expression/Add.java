package expression;

import java.math.BigDecimal;

public class Add extends BinaryOperation {

    public Add(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a + b;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal a, BigDecimal b) {
        return a.add(b);
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
        return 1600;
    }

    @Override
    public String getSign() {
        return "+";
    }
}
