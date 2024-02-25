package game;

public class CircleBoard extends AbstractMnkBoard {
    public CircleBoard(int diameter, int k) {
        super(diameter, diameter, k);
    }

    private int getDiameter() {
        return getRows();
    }

    @Override
    protected Cell[][] generateCells() {
        Cell[][] cells = new Cell[getRows()][getColumns()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                cells[i][j] = inCircle(i, j) ? Cell.E : Cell.B;
            }
        }
        return cells;
    }

    private boolean inCircle(int x, int y) {
        int center = getDiameter() - 1;
        int r1 = center - 2 * x;
        int r2 = center - 2 * y;
        return (r1 * r1 + r2 * r2) <= getDiameter() * getDiameter();
    }

    @Override
    protected boolean inBoard(int row, int column) {
        return 0 <= row && row < getRows()
                && 0 <= column && column < getColumns()
                && getCell(row, column) != Cell.B;
    }
}
