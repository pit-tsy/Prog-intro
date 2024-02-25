package expression;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x;
        while (true) {
            try {
                System.out.print("Enter x: ");
                x = in.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Excepted integer number. Please try again.");
                in.nextLine();
            }
        }
        GeneralExpression exp = new Add(
                new Subtract(
                        new Multiply(new Variable("x"), new Variable("x")),
                        new Multiply(new Const(2), new Variable("x"))
                ),
                new Const(1)
        );
        System.out.println(exp.evaluate(x));
    }
}
