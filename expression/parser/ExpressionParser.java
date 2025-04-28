package expression.parser;

import expression.*;
import expression.exceptions.parseexceptions.*;

import java.util.List;

public class ExpressionParser  implements TripleParser {

    @Override
    public TripleExpression parse(String expression) {
        return new TripleParser(new StringSource(expression)).parse();
    }

    protected static class TripleParser extends BaseParser {
        protected  static final List<String> signes = List.of(
                "*", "/", "+", "-", "&", "^", "|", "min", "max", ">>", ">>>", "<<"
        );

        protected String nextBinSign = "";

        protected TripleParser(CharSource source) {
            super(source);
        }

        public TripleExpression parse() {
            final TripleExpression result = parseExpression();
            if (eof()) {
                return result;
            }
            throw error("End of expression expected");
        }

        protected GeneralExpression parseExpression() {
            GeneralExpression exp;
            try {
                exp = parseValue();
            } catch (SimpleBlockNotFoundException e) {
                throw new NoFirstElementException("expected first value of expression", getPos());
            }
            return parseBinaryOp(exp, takeBinarySign(), "");
        }

        protected GeneralExpression parseBinaryOp(GeneralExpression exp, String prevSign, String lastCallSign) {
            while(!prevSign.isEmpty()) {
                if (getBinPriority(lastCallSign) >= getBinPriority(prevSign)) {
                    return exp;
                }

                GeneralExpression value;
                value = parseValue();
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
            nextBinSign = take(signes);
            return nextBinSign;
        }


        protected GeneralExpression parseValue() {
            skipWhitespace();

            GeneralExpression exp = null;
            if (test('(')) {
                exp = parseWrappedExpression();
            } else if (take('-')) {
                if (between('0', '9')) {
                    exp = new Const(parseInt(true));
                } else {
                    exp = new Negate(parseValue());
                }
            } else if (take("l1")) {
                return new L1(parseValue());
            }  else if (take("t1")) {
                return new T1(parseValue());
            } else if (take("low")) {
                return new Low(parseValue());
            } else if (take("high")) {
                return new High(parseValue());
            } else if (between('0', '9')) {
                exp = new Const(parseInt(false));
            } else if (test('x', 'y', 'z')) {
                exp = new Variable(take());
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
                case ">>", ">>>", "<<" -> 300;
                default -> -1;
            };
        }

        protected BinaryOperation getBinOp(String sign, GeneralExpression exp1, GeneralExpression exp2) {
            return switch (sign) {
                case "+" -> new Add(exp1, exp2);
                case "-" -> new Subtract(exp1, exp2);
                case "*" -> new Multiply(exp1, exp2);
                case "/" -> new Divide(exp1, exp2);
                case "&" -> new And(exp1, exp2);
                case "^" -> new Xor(exp1, exp2);
                case "|" -> new Or(exp1, exp2);
                default -> throw error("excepted binary sign");
            };
        }


        protected int parseInt(boolean negative) {
            StringBuilder number = new StringBuilder();
            if (negative) {
                number.append('-');
            }
            while(between('0', '9')) {
                number.append(take());
            }

            return Integer.parseInt(number.toString());
        }

        protected GeneralExpression parseWrappedExpression() {
            take();
            GeneralExpression expression = parseExpression();
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


