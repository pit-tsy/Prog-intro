package expression.exceptions;

import expression.GeneralExpression;
import expression.Max;

public class CheckedMax extends Max {
    public CheckedMax(GeneralExpression exp1, GeneralExpression exp2) {
        super(exp1, exp2);
    }
}
