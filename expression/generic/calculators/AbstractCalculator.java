package expression.generic.calculators;

public abstract class AbstractCalculator<T> implements Calculator<T> {
    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }
}
