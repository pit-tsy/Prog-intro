package expression.exceptions;

import expression.AbstractExpression;
import expression.ArithmeticRightShift;
import expression.GeneralExpression;

public class CheckedArithmeticRightShift extends ArithmeticRightShift {
    public CheckedArithmeticRightShift(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }
}
