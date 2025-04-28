package expression.generic.calculators;


public class CalculatorCreator {
    public static Calculator<?> create(String mode) {
        return switch (mode) {
            case "i" -> new IntegerCalculator(IntCalculationMode.CHECK_OVERFLOW);
            case "u" -> new IntegerCalculator(IntCalculationMode.NATIVE);
            case "sat" -> new IntegerCalculator(IntCalculationMode.SAT);
            case "bi" -> new BigIntegerCalculator();
            case "d" -> new DoubleCalculator();
            case "b" -> new ByteCalculator();
            default -> throw new IllegalStateException("Unexpected mode: " + mode);
        };
    }
}
