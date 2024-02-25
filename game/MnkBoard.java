package game;

import java.util.Arrays;

public class MnkBoard extends AbstractMnkBoard {
    MnkBoard(int m, int n, int k) {
        super(m, n, k);
    }

    @Override
    protected Cell[][] generateCells() {
        Cell[][] cells = new Cell[getRows()][getColumns()];
        for (var row : cells) {
            Arrays.fill(row, Cell.E);
        }
        return cells;
    }

    @Override
    protected boolean inBoard(int row, int column) {
        return 0 <= row && row < getRows()
                && 0 <= column && column < getColumns();
    }
}
