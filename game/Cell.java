package game;

import java.util.Map;

public enum Cell {
    X, O, E, B;
    static final Map<Cell, Character> SYMBOLS = Map.of(
            X, 'X',
            O, 'O',
            E, '.',
            B, ' '
    );
}
