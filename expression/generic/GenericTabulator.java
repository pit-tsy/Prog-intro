package expression.generic;

import expression.exceptions.calcexceptions.CalculationException;
import expression.exceptions.parseexceptions.ParseException;
import expression.generic.calculators.*;
import expression.generic.expressions.TripleExpression;
import expression.generic.parser.ExpressionParser;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        return createTable(CalculatorCreator.create(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    public <T> Object[][][] createTable(Calculator<T> calculator, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        TripleExpression<T> parsedExpression = new ExpressionParser<T>().parse(calculator, expression);
        Object[][][] table = new Object[getLen(x1, x2)][getLen(y1, y2)][getLen(z1, z2)];
        for (int i = 0; i < getLen(x1, x2); i++) {
            for (int j = 0; j < getLen(y1, y2); j++) {
                for (int k = 0; k < getLen(z1, z2); k++) {
                    try {
                        table[i][j][k] = parsedExpression.evaluate(
                                calculator.valueOf(x1 + i),
                                calculator.valueOf(y1 + j),
                                calculator.valueOf(z1 + k)
                        );
                    } catch (CalculationException e) {
                        table[i][j][k] = null;
                    }
                }
            }
        }

        return table;
    }

    private int getLen(int l, int r) {
        return r - l + 1;
    }
}
