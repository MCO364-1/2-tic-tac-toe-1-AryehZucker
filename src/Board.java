public class Board {
    private static final int SIZE = 3;
    private Player[][] pieces = new Player[SIZE][SIZE];

    public Player getPiece(int row, int col) {
        validatePosition(row, col);
        return pieces[row][col];
    }

    public void placePiece(int row, int col, Player p) {
        validatePosition(row, col);
        ensureVacant(row, col);
        pieces[row][col] = p;
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE)
            throw new IllegalArgumentException("Expected 0<=row,col<=2 but was: row=" + row + ", col=" + col);
    }

    private void ensureVacant(int row, int col) {
        if (getPiece(row, col) != null) {
            throw new IllegalArgumentException("Position row=" + row + ", col=" + col + "is not vacant");
        }
    }

    public boolean isFull() {
        for (int row = 0; row < pieces.length; row++) {
            for (int col = 0; col < pieces[row].length; col++) {
                if (getPiece(row, col) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean has3InARow() {
        return has3InARowStraight() || has3InARowDiagonal();
    }

    private boolean has3InARowStraight() {
        for (int i = 0; i < SIZE; i++) {
            if (pieces[i][i] == null)
                continue;
            if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] ||
                    pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i])
                return true;
        }
        return false;
    }

    private boolean has3InARowDiagonal() {
        if (pieces[1][1] == null)
            return false;
        return pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2] ||
                pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0];
    }
}
