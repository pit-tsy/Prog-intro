package game;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserContact {
    public static boolean ask(PrintStream out, String query, Scanner in) {
        while (true) {
            out.println(query + " [y / n]");
            String answer = in.next();
            if (answer.equals("y")) return true;
            if (answer.equals("n")) return false;
            out.println("You didn't enter 'y' or 'n'. Please, try again: ");
        }
    }

    public static int chooseOptions(PrintStream out, Scanner in, String query, String... options) {
        while (true) {
            out.print("Choose " + query + ' ' + Arrays.toString(options) + ": ");
            String answer = in.next();
            for (int i = 0; i < options.length; i++) {
                if (answer.equals(options[i])) return i + 1;
            }
            out.println("You have not entered any of the possible options. Please, try again: ");
        }
    }

    public static Board chooseBoard(PrintStream out, Scanner in) {
        int boardType = chooseOptions(out, in, "board type", "mnk", "circle");
        while (true) {
            try {
                if (boardType == 1) {
                    int m = enterParameter(out, "number of rows", in);
                    int n = enterParameter(out, "number of columns", in);
                    int k = enterParameter(out, "length of the winning line", in);
                    return new MnkBoard(m, n, k);
                } else {
                    int diam = enterParameter(out, "diameter", in);
                    int k = enterParameter(out, "length of the winning line", in);
                    return new CircleBoard(diam, k);
                }
            } catch (IllegalArgumentException e) {
                out.println(e.getMessage() + ". Please try again: ");
            } catch (OutOfMemoryError e) {
                out.println("Ohhh... You have overflowed memory, enter smaller numbers: ");
            }
        }
    }

    public static int enterParameter(PrintStream out, String name, Scanner in) {
        while (true) {
            try {
                out.print("Enter " + name + ": ");
                return in.nextInt();
            } catch (InputMismatchException e) {
                out.println("Wrong input, integer number(not very big) was excepted. Please try again: ");
                in.nextLine();
            }
        }
    }

    public static String resultToString(int result) {
        if (result == 0) {
            return "DRAW";
        } else if (result > 0) {
            return "Player" + result + " won";
        } else {
            return "UNKNOWN";
        }
    }
}
