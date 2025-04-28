package expression.exceptions;

import expression.GeneralExpression;
import expression.Min;

public class CheckedMin extends Min {
    public CheckedMin(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }
}
