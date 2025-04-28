package expression.generic;

import expression.exceptions.parseexceptions.ParseException;

import java.util.Set;

public class Main {
    private static final Set<String> MODES = Set.of("i", "d", "bi");
    private static final int l = -2;
    private static final int r = 2;
    private static final int length = r - l + 1;


    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: <mode> <expression>");
            return;
        }

        String mode = "";
        if (!args[0].isEmpty()) {
            mode = args[0].substring(1);
        }
        if (!MODES.contains(mode)) {
            System.out.println("Expected modes : -i, -bi, -d");
            return;
        }

        Object[][][] table;
        try {
            table = new GenericTabulator().tabulate(mode, args[1], l, r, l, r, l, r);
        } catch (ParseException e) {
            System.out.println("incorrect expression");
            return;
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    System.out.format("%2s ", table[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
