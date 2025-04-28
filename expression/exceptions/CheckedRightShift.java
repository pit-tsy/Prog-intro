package expression.exceptions;

import expression.BinaryOperation;
import expression.GeneralExpression;
import expression.RightShift;

import java.math.BigDecimal;

public class CheckedRightShift extends RightShift {

    public CheckedRightShift(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }
}
