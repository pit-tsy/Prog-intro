package expression.exceptions;

import expression.Divide;
import expression.GeneralExpression;
import expression.exceptions.calcexceptions.CalculationException;
import expression.exceptions.calcexceptions.DivisionByZeroException;
import expression.exceptions.calcexceptions.OverflowException;

import java.math.BigDecimal;

public class CheckedDivide extends Divide {
    public CheckedDivide(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }

    @Override
    protected int doOperation(int a, int b) {
        if (b == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new OverflowException("overflow");
        }
        return a / b;
    }

    @Override
    protected BigDecimal doOperation(BigDecimal a, BigDecimal b) {
        if (b.equals(0)) {
            throw new DivisionByZeroException("division by zero");
        }
        return a.divide(b);
    }
}
