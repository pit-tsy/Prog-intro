package expression.generic.calculators;

public interface Calculator<T> {
    T negate(T a);
    T add(T a, T b);
    T subtract(T a, T b);
    T multiply(T a, T b);
    T divide(T a, T b);
    T max(T a, T b);
    T min(T a, T b);
    T count(T a);

    T parseConst(String str);
    T valueOf(int a);
}
