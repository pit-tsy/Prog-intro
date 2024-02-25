package expression;

import java.math.BigDecimal;

public class T1 extends UnaryOperation {
    public T1(GeneralExpression exp) {
        super(exp);
    }

    @Override
    public String getSign() {
        return "t1";
    }

    @Override
    protected int doOperation(int x) {
        int i = 0;
        while (i <= 31 && ((x >> i) & 1) == 1) {
            i++;
        }
        return i;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal x) {
        throw new UnsupportedOperationException("Unsupported BigDecimal t1");
    }
}
