package expression;

import java.math.BigDecimal;

public class RightShift extends BinaryOperation {
    public RightShift(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        return (a >> b);
    }

    @Override
    protected BigDecimal doOperation(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException("unsupported bigDecimal operation");
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
        return 300;
    }

    @Override
    public String getSign() {
        return ">>";
    }
}
