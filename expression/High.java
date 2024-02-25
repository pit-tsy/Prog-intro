package expression;

import java.math.BigDecimal;

public class High extends UnaryOperation {
    public High(GeneralExpression exp) {
        super(exp);
    }

    @Override
    public String getSign() {
        return "high";
    }

    @Override
    protected int doOperation(int x) {
        int i = 31;
        while (i >= 0 && ((x >> i) & 1) != 1) {
            i--;
        }
        return i >= 0 ? (1 << i) : 0;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal x) {
        throw new UnsupportedOperationException("Unsupported BigDecimal high");
    }
}
