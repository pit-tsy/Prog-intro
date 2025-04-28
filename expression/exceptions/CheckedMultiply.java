package expression.exceptions;

import expression.GeneralExpression;
import expression.Multiply;
import expression.exceptions.calcexceptions.CalculationException;
import expression.exceptions.calcexceptions.OverflowException;

import javax.swing.event.InternalFrameEvent;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        if (checkOverflow(a, b)) {
            throw new OverflowException("overflow");
        }
        return a * b;
    }

    public static boolean checkOverflow(int a, int b) {
        if (a == 0 || b == 0) {
            return false;
        } else if (b == -1) {
            return a == Integer.MIN_VALUE;
        }

        if (a > 0) {
            if (b < 0) {
                return a > Integer.MIN_VALUE / b;
            } else {
                return a > Integer.MAX_VALUE / b;
            }
        } else {
            if (b < 0) {
                return a < Integer.MAX_VALUE / b;
            } else {
                return a < Integer.MIN_VALUE / b;
            }
        }
    }
}
