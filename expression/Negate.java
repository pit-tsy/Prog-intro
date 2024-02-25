package expression;

import java.math.BigDecimal;

public class Negate extends UnaryOperation {
    public Negate(GeneralExpression exp) {
        super(exp);
    }

    @Override
    public String getSign() {
        return "-";
    }

    @Override
    protected int doOperation(int x) {
        return -x;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal x) {
        return x.negate();
    }
}
