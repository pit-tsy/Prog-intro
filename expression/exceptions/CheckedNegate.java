package expression.exceptions;

import expression.GeneralExpression;
import expression.Negate;
import expression.exceptions.calcexceptions.CalculationException;
import expression.exceptions.calcexceptions.OverflowException;

public class CheckedNegate extends Negate {
    public CheckedNegate(GeneralExpression exp) {
        super(exp);
    }

    @Override
    public int doOperation(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return -x;
    }
}
