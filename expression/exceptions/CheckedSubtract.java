package expression.exceptions;

import expression.GeneralExpression;
import expression.Subtract;
import expression.exceptions.calcexceptions.CalculationException;
import expression.exceptions.calcexceptions.OverflowException;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        if (checkOverflow(a, b)) {
            throw new OverflowException("overflow");
        }
        return a - b;
    }

    public static boolean checkOverflow(int a, int b) {
        return b >= 0 && a < Integer.MIN_VALUE + b
                || b < 0 && a > Integer.MAX_VALUE + b;
    }
}
