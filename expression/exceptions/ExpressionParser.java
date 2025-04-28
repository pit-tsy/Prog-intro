package expression.exceptions;

import expression.*;
import expression.exceptions.parseexceptions.*;
import expression.parser.CharSource;
import expression.parser.StringSource;

import java.util.List;
import java.util.Map;

public class ExpressionParser extends expression.parser.ExpressionParser implements TripleParser, ListParser {

    @Override
    public TripleExpression parse(String expression) {
        return new CheckedTripleParser(new StringSource(expression)).parse();
    }

    @Override
    public ListExpression parse(String expression, List<String> variables) throws ParseException {
        return new CheckedTripleParser(new StringSource(expression)).parse(variables);
    }

    private static class CheckedTripleParser extends TripleParser {

        private static final Map<String, String> BRACKETS = Map.of(
                "(", ")",
                "{", "}",
                "[", "]"
        );

        private List<String> vars;

        protected CheckedTripleParser(CharSource source) {
            super(source);
        }

        @Override
        public TripleExpression parse() {
            return parse(List.of("x", "y", "z"));
        }

        public GeneralExpression parse(final List<String> variables) {
            vars = variables;
            final GeneralExpression result = parseExpression();
            if (eof()) {
                return result;
            }
            throw new ExpectedEndOfExpressionException("End of expression expected", getPos());
        }

        private String messageError(String message) {
            return "Position " + getPos() + ": " + message;
        }

        @Override
        protected void expect(char expected) {
            if (!take(expected)) {
                throw new NotExceptedSymbolException("Expected '" + expected + "', found '" + take() + "'", getPos());
            }
        }

        @Override
        protected GeneralExpression parseExpression() {
            GeneralExpression exp;
            try {
                exp = parseValue();
            } catch (SimpleBlockNotFoundException e) {
                throw new NoFirstElementException("expected first value of expression", getPos());
            }
            return parseBinaryOp(exp, takeBinarySign(), "");
        }

        @Override
        protected GeneralExpression parseBinaryOp(GeneralExpression exp, String prevSign, String lastCallSign) {
            while (!prevSign.isEmpty()) {
                if (getBinPriority(lastCallSign) >= getBinPriority(prevSign)) {
                    return exp;
                }

                GeneralExpression value;
                try {
                    value = parseValue();
                } catch (SimpleBlockNotFoundException e) {
                    throw new SecondOperandNotFoundException("value was expected after the binary sign", getPos());
                }
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

        @Override
        protected String takeBinarySign() {
            nextBinSign = take(signes);
            if (nextBinSign.isEmpty() && !bufferIsClear()) {
                throw new InvalidBinSignException("incorrect binary operation", getPos());
            }
            return nextBinSign;
        }

        @Override
        protected GeneralExpression parseValue() throws ParseException {
            skipWhitespace();

            GeneralExpression exp = null;
            if (test('(', '{', '[')) {
                exp = parseWrappedExpression();
            } else if (take('-')) {
                if (between('0', '9')) {
                    exp = new Const(parseInt(true));
                } else {
                    exp = new CheckedNegate(parseValue());
                }
            } else if (between('0', '9')) {
                exp = new Const(parseInt(false));
            } else {
                String var = take(vars);
                if (!var.isEmpty()) {
                    exp = new Variable(var, vars.indexOf(var));
                }
            }

            if (exp == null) {
                throw new SimpleBlockNotFoundException("excepted simple-value", getPos());
            }

            skipWhitespace();
            return exp;
        }

        @Override
        protected int parseInt(boolean negative) {
            if (take('0')) {
                notExpectLetters();
                return 0;
            }

            int result;
            try {
                result = super.parseInt(negative);
            } catch (NumberFormatException e) {
                throw new IntOverflowException("integer overflow", getPos());
            }
            notExpectLetters();

            return result;
        }

        private void notExpectLetters() {
            if (between('a', 'z') || between('A', 'Z')) {
                throw new NotExceptedSymbolException("not excepted a-z symbols after the value", getPos());
            }
        }

        @Override
        protected GeneralExpression parseWrappedExpression() {
            String bracket = "" + take();
            GeneralExpression expression;
            try {
                expression = parseExpression();
            } catch (NoFirstElementException e) {
                throw new EmptyBracketsExceptions("expected expression", getPos());
            }

            if (!take(BRACKETS.get(bracket))) {
                throw new UnpairedBracketsException("Expected: " + BRACKETS.get(bracket) + " but got: " + take(), getPos());
            }

            return expression;
        }

        @Override
        protected BinaryOperation getBinOp(String sign, GeneralExpression exp1, GeneralExpression exp2) {
            return switch (sign) {
                case "+" -> new CheckedAdd(exp1, exp2);
                case "-" -> new CheckedSubtract(exp1, exp2);
                case "*" -> new CheckedMultiply(exp1, exp2);
                case "/" -> new CheckedDivide(exp1, exp2);
                case "min" -> new CheckedMin(exp1, exp2);
                case "max" -> new CheckedMax(exp1, exp2);
                case ">>" -> new CheckedRightShift(exp1, exp2);
                case ">>>" -> new CheckedArithmeticRightShift(exp1, exp2);
                case "<<" -> new CheckedLeftShift(exp1, exp2);
                default -> throw new ParseException("unknown sign");
            };
        }
    }
}


