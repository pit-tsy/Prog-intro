package expression;

import java.math.BigDecimal;

public class Low extends UnaryOperation {
    public Low(GeneralExpression exp) {
        super(exp);
    }

    @Override
    public String getSign() {
        return "low";
    }

    @Override
    protected int doOperation(int x) {
        int i = 0;
        while (i < 32 && ((x >> i) & 1) != 1) {
            i++;
        }
        return i >= 32 ? 0 : (1 << i);
    }

    @Override
    protected BigDecimal doOperation(BigDecimal x) {
        throw new UnsupportedOperationException("Unsupported BigDecimal lowhign");
    }
}
