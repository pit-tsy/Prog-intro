package expression;

import java.math.BigDecimal;

public abstract class BinaryOperation extends AbstractExpression implements Operation {
    private final GeneralExpression exp1;
    private final GeneralExpression exp2;

    public BinaryOperation(GeneralExpression exp1, GeneralExpression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public int evaluate(int x) {
        return doOperation(exp1.evaluate(x), exp2.evaluate(x));
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return doOperation(exp1.evaluate(x), exp2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return doOperation(exp1.evaluate(x, y, z), exp2.evaluate(x, y, z));
    }

    protected abstract int doOperation(int a, int b);

    protected abstract BigDecimal doOperation(BigDecimal a, BigDecimal b);

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
        if (exp2 instanceof Operation exp) {
            if (exp instanceof CombineIntoOne op) {
                return !getClass().equals(op.getClass());
            }

            return getPriority() > exp.getPriority()
                    || getPriority() == exp.getPriority()
                    && (signRequiresBracket() || opRequiresBracket(exp));
        }
        return false;
    }

    private boolean signRequiresBracket() {
        return !isCommutative();
    }

    private boolean opRequiresBracket(Operation op) {
        if (op instanceof BinaryOperation binOp) {
            return !binOp.isDivisible();
        }
        return false;
    }

    protected abstract boolean isDivisible();

    protected abstract boolean isCommutative();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperation operation) {
            return getClass().equals(operation.getClass())
                    && exp1.equals(operation.exp1)
                    && exp2.equals(operation.exp2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (32 * exp1.hashCode() + exp2.hashCode()) * 32 + getSign().hashCode();
    }
}
