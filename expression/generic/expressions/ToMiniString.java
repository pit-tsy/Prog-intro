package expression.generic.expressions;

public interface ToMiniString {
    default String toMiniString() {
        return toString();
    }
}
