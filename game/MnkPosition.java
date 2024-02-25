package game;

public class MnkPosition implements Position {
    private final AbstractMnkBoard board;

    public MnkPosition(AbstractMnkBoard board) {
        this.board = board;
    }

    @Override
    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return board.getCell(r, c);
    }

    @Override
    public int getRows() {
        return board.getRows();
    }

    @Override
    public int getColumns() {
        return board.getColumns();
    }

    @Override
    public String toStringFormat() {
        return board.toStringFormat();
    }
}
