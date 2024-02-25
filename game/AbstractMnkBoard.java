package game;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class AbstractMnkBoard implements Board, Position {
    protected final Cell[][] cells;
    private final int m, n, k;
    protected Cell turn;
    private int emptyCells;
    private int winLines;

    public AbstractMnkBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;

        if (k <= 0) {
            throw new IllegalArgumentException("K is positive number");
        }
        if (k > min(getRows(), getColumns())) {
            throw new IllegalArgumentException("K cannot be greater than row or column of board");
        }

        this.cells = generateCells();
        this.emptyCells = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j] == Cell.E) emptyCells += 1;
            }
        }
        countWinLines();

        turn = Cell.X;
    }

    protected abstract Cell[][] generateCells();

    protected abstract boolean inBoard(final int row, final int column);

    @Override
    public void clear() {
        emptyCells = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (inBoard(i, j)) {
                    cells[i][j] = Cell.E;
                    emptyCells += 1;
                }
            }
        }
        turn = Cell.X;
        countWinLines();
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        int row = move.getRow();
        int column = move.getColumn();
        cells[row][column] = move.getValue();
        emptyCells -= 1;

        if (isWin(row, column)) return Result.WIN;
        if (checkDraw(row, column)) return Result.DRAW;

        turn = turn == Cell.X ? Cell.O : Cell.X;

        return Result.UNKNOWN;
    }

    protected boolean isWin(int row, int col) {
        return countTurnInLine(row, col, 1, 0) + countTurnInLine(row, col, -1, 0) > k
                || countTurnInLine(row, col, 0, 1) + countTurnInLine(row, col, 0, -1) > k
                || countTurnInLine(row, col, 1, 1) + countTurnInLine(row, col, -1, -1) > k
                || countTurnInLine(row, col, 1, -1) + countTurnInLine(row, col, -1, 1) > k;
    }

    private boolean checkDraw(int row, int col) {
        if (emptyCells == 0) return true;

        winLines -= countWinLines(row, col, turn == Cell.X ? Cell.O : Cell.X);
        return winLines == 0;
    }

    protected void countWinLines() {
        winLines = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int count = countInLine(row, col, 0, 1, Cell.E);
                winLines += max(0, count - k + 1);
                col += max(0, count - 1);
            }
        }

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                int count = countInLine(row, col, 1, 0, Cell.E);
                winLines += max(0, count - k + 1);
                row += max(0, count - 1);
            }
        }

        for (int j = 0; j < n; j++) {
            int col = j;
            for (int row = 0; row < m && col < n; row++) {
                int count = countInLine(row, col, 1, 1, Cell.E);
                winLines += max(0, count - k + 1);
                col += max(1, count);
                ;
                row += max(0, count - 1);
            }
            col = j;
            for (int row = 0; row < m && col < n; row++) {
                int count = countInLine(row, col, 1, -1, Cell.E);
                winLines += max(0, count - k + 1);
                col += max(1, count);
                row += max(0, count - 1);
            }
        }

        for (int i = 1; i < m; i++) {
            int row = i;
            for (int col = 0; col < n && row < m; col++) {
                int count = countInLine(row, col, 1, 1, Cell.E);
                winLines += max(0, count - k + 1);
                row += max(1, count);
                ;
                col += max(0, count - 1);
            }
            row = i;
            for (int col = n - 1; col < n && row < m; col++) {
                int count = countInLine(row, col, 1, -1, Cell.E);
                winLines += max(0, count - k + 1);
                row += max(1, count);
                col += max(0, count - 1);
            }
        }

        winLines *= 2;
    }

    private int countWinLines(int row, int col, Cell cell) {
        int result = 0;
        result += countWinLines(row, col, 0, 1, cell);
        result += countWinLines(row, col, 1, 0, cell);
        result += countWinLines(row, col, 1, 1, cell);
        result += countWinLines(row, col, -1, 1, cell);
        return result;
    }

    private int countWinLines(int row, int col, int dr, int dc, Cell cell) {
        int count = max((countInLine(row + dr, col + dc, dr, dc, Cell.E, cell)
                        + countInLine(row - dr, col - dc, -dr, -dc, Cell.E, cell) + 1) - k + 1,
                0);
        return min(count, k);
    }

    @Override
    public int getRows() {
        return m;
    }

    @Override
    public int getColumns() {
        return n;
    }

    @Override
    public Position getPosition() {
        return new MnkPosition(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    private int countTurnInLine(int row, int column, int dr, int dc) {
        return countInLine(row, column, dr, dc, turn);
    }

    private int countInLine(int row, int column, int dr, int dc, Cell... targetCells) {
        int answer = 0;
        while (inBoard(row, column) && inArray(cells[row][column], targetCells)) {
            answer += 1;
            row += dr;
            column += dc;
        }
        return answer;
    }

    private boolean inArray(Cell cell, Cell... array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == cell) return true;
        }
        return false;
    }

    @Override
    public boolean isValid(final Move move) {
        return inBoard(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }


    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toStringFormat() {
        return toString();
    }

    @Override
    public String toString() {
        final int maxNumLen = max(numLen(n), numLen(m)) + 1;
        final StringBuilder sb = new StringBuilder(String.format("%" + maxNumLen + "c", ' '));
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%" + maxNumLen + "d", i));
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(String.format("%" + maxNumLen + "d", r));
            for (int c = 0; c < n; c++) {
                sb.append(String.format("%" + maxNumLen + "c", Cell.SYMBOLS.get(cells[r][c])));
            }
        }
        return sb.toString();
    }

    private int numLen(int x) {
        return Integer.toString(x).length();
    }
}
