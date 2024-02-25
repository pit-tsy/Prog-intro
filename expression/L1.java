package expression;

import java.math.BigDecimal;

public class L1 extends UnaryOperation {

    public L1(GeneralExpression exp) {
        super(exp);
    }

    @Override
    public String getSign() {
        return "l1";
    }

    @Override
    protected int doOperation(int x) {
        int i = 0;
        while (i <= 31 && (x >> (31 - i) & 1) == 1) {
            i++;
        }
        return i;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal x) {
        throw new UnsupportedOperationException("Unsupported BigDecimal l1");
    }
}
