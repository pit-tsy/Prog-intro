package expression.exceptions;

import expression.GeneralExpression;
import expression.LeftShift;

public class CheckedLeftShift extends LeftShift {
    public CheckedLeftShift(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }
}
