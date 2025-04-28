package expression.generic.parser;

import expression.generic.expressions.TripleExpression;

import java.util.List;
import expression.generic.expressions.*;
import expression.generic.calculators.Calculator;

public class ExpressionParser<T>  implements TripleParser<T> {

    @Override
    public TripleExpression<T> parse(Calculator<T> calculator, String expression) {
        return new TripleParser<T>(calculator, new StringSource(expression)).parse();
    }

    protected static class TripleParser<T> extends BaseParser {
        private static final List<String> SIGNS = List.of(
                "*", "/", "+", "-", "min", "max"
        );
        private final Calculator<T> calculator;
        private String nextBinSign = "";

        protected TripleParser(Calculator<T> calculator, CharSource source) {
            super(source);
            this.calculator = calculator;
        }

        public TripleExpression<T> parse() {
            final TripleExpression<T> result = parseExpression();
            if (eof()) {
                return result;
            }
            throw error("End of expression expected");
        }

        protected TripleExpression<T> parseExpression() {
            return parseBinaryOp(parseValue(), takeBinarySign(), "");
        }

        protected TripleExpression<T> parseBinaryOp(TripleExpression<T> exp, String prevSign, String lastCallSign) {
            while(!prevSign.isEmpty()) {
                if (getBinPriority(lastCallSign) >= getBinPriority(prevSign)) {
                    return exp;
                }

                TripleExpression<T> value = parseValue();
                String sign = takeBinarySign();
                if (getBinPriority(prevSign) >= getBinPriority(sign)) {
                    exp = getBinOp(prevSign, exp, value);
                    prevSign = sign;
                } else {
                    exp = getBinOp(prevSign, exp, parseBinaryOp(value, sign, prevSign));
                    prevSign = nextBinSign;
                }
            }

            return exp;
        }

        protected String takeBinarySign() {
            nextBinSign = take(SIGNS);
            return nextBinSign;
        }


        protected TripleExpression<T> parseValue() {
            skipWhitespace();

            TripleExpression<T> exp = null;
            if (test('(')) {
                exp = parseWrappedExpression();
            } else if (take('-')) {
                if (between('0', '9')) {
                    exp = new Const<>(calculator, parseNumber(true));
                } else {
                    exp = new Negate<>(parseValue());
                }
            } else if (between('0', '9')) {
                exp = new Const<T>(calculator, parseNumber(false));
            } else if (take("count")) {
                exp = new Count<T>(parseValue());
            } else if (test('x', 'y', 'z')) {
                exp = new Variable<T>(calculator, take());
            }

            if (exp == null) {
                throw error("excepted value");
            }

            skipWhitespace();
            return exp;
        }

        protected int getBinPriority(String sign) {
            return switch (sign) {
                case "*", "/" -> 2000;
                case "+", "-" -> 1600;
                case "&" -> 1000;
                case "^" -> 999;
                case "|" -> 998;
                case "min", "max" -> 400;
                default -> -1;
            };
        }

        protected BinaryOperation<T> getBinOp(String sign, TripleExpression<T> exp1, TripleExpression<T> exp2) {
            return switch (sign) {
                case "+" -> new Add<T>(exp1, exp2);
                case "-" -> new Subtract<T>(exp1, exp2);
                case "*" -> new Multiply<T>(exp1, exp2);
                case "/" -> new Divide<T>(exp1, exp2);
                case "min" -> new Min<T>(exp1, exp2);
                case "max" -> new Max<T>(exp1, exp2);
                default -> throw error("excepted binary sign");
            };
        }


        protected T parseNumber(boolean negative) {
            StringBuilder number = new StringBuilder();
            if (negative) {
                number.append('-');
            }
            while(between('0', '9') || test('.')) {
                number.append(take());
            }

            return calculator.parseConst(number.toString());
        }

        protected TripleExpression<T> parseWrappedExpression() {
            take();
            TripleExpression<T> expression = parseExpression();
            expect(')');
            return expression;
        }

        protected void skipWhitespace() {
            while (nextIsWhiteSpace()) {
                take();
            }
        }
    }
}


