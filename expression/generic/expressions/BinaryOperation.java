package expression.generic.expressions;

public abstract class BinaryOperation<T> extends AbstractExpression<T> implements Operation {
    private final TripleExpression<T> exp1;
    private final TripleExpression<T> exp2;

    public BinaryOperation(TripleExpression<T> exp1, TripleExpression<T> exp2) {
        super(exp1.getCalculator());
        if (!calculator.equals(exp2.getCalculator())) {
            throw new IllegalArgumentException("exp1 and exp2 must have same calculators");
        }

        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return doOperation(exp1.evaluate(x, y, z), exp2.evaluate(x, y, z));
    }

    protected abstract T doOperation(T a, T b);

    @Override
    public void toString(StringBuilder builder) {
        builder.append('(');
        exp1.toString(builder);
        builder.append(" ").append(getSign()).append(" ");
        exp2.toString(builder);
        builder.append(')');
    }

    @Override
    public void toMiniString(StringBuilder builder, boolean brackets) {
        if (brackets) builder.append('(');
        exp1.toMiniString(builder, needBrackets1());
        builder.append(" ").append(getSign()).append(" ");
        exp2.toMiniString(builder, needBrackets2());
        if (brackets) builder.append(')');
    }

    private boolean needBrackets1() {
        if (exp1 instanceof Operation exp) {
            return getPriority() > exp.getPriority();
        }
        return false;
    }

    private boolean needBrackets2() {
        if (!(exp2 instanceof Operation exp)) {
            return false;
        }
        if (exp instanceof CombineIntoOne op) {
            return !getClass().equals(op.getClass());
        }

        return getPriority() > exp.getPriority()
                || getPriority() == exp.getPriority()
                && (signRequiresBracket() || opRequiresBracket(exp));
    }

    private boolean signRequiresBracket() {
        return !isCommutative();
    }

    private boolean opRequiresBracket(Operation op) {
        if (op instanceof BinaryOperation<?> binOp) {
            return !binOp.isDivisible();
        }
        return false;
    }

    protected abstract boolean isDivisible();

    protected abstract boolean isCommutative();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation<?> operation) {
            return getClass().equals(operation.getClass())
                    && exp1.equals(operation.exp1)
                    && exp2.equals(operation.exp2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (32 * exp1.hashCode() + exp2.hashCode()) * 32 + getClass().hashCode();
    }
}
