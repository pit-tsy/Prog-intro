package expression.exceptions;

import expression.GeneralExpression;
import expression.ListExpression;
import expression.TripleExpression;
import expression.exceptions.calcexceptions.DivisionByZeroException;
import expression.exceptions.calcexceptions.OverflowException;
import expression.exceptions.parseexceptions.ParseException;

import java.math.BigInteger;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        ExpressionParser parser = new ExpressionParser();
//        TripleExpression expression = parser.parse("1000000*x*x*x*x*x/(x-1)");
        ListExpression expression = parser.parse("xxx min x + xx + x +5+ x + x + xx", List.of("x", "xxx", "xx"));
        System.out.println(expression.toMiniString());

        for (int x = 0; x <= 10; x++) {
            try {
                System.out.println(expression.evaluate(List.of(x, 0, 0)));
            } catch (OverflowException e) {
                System.out.println("overflow");
            } catch (DivisionByZeroException e) {
                System.out.println("division by zero");
            }
        }
    }
}
