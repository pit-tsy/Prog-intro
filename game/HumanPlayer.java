package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;


public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            try {
                out.println("Position");
                out.println(position.toStringFormat());
                out.println(cell + "'s move");
                out.println("Enter row and column");
                final Move move = new Move(in.nextInt(), in.nextInt(), cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Move " + move + " is invalid");
            } catch (InputMismatchException e) {
                out.println("Wrong input, excepted 2 integer numbers. Try again: ");
                in.nextLine();
            }
        }
    }
}
