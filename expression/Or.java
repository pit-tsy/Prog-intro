package expression;

import java.math.BigDecimal;

public class Or extends BinaryOperation {

    public Or(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        return a | b;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("Unsupported BigDecimal xor");
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
        return 998;
    }

    @Override
    public String getSign() {
        return "|";
    }
}
