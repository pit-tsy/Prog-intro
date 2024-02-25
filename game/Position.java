package game;

public interface Position {
    boolean isValid(Move move);

    Cell getCell(int r, int c);

    int getRows();

    int getColumns();

    String toStringFormat();
}
