package expression.parser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("x * (x - 2)*x + 1").toMiniString());
        System.out.println(parser.parse("                  10+11                      "));
        System.out.println(parser.parse("                  30 - -30 * (123 - 234 * (x + z * (x + y) * (y + y + x + x)))       ").toMiniString());
    }
}
