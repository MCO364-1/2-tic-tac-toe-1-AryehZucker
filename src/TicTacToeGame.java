public class TicTacToeGame {
    private Board board = new Board();
    private Player currentPlayer = Player.X;
    private boolean gameOver = false;

    /**
     * @return the player that moved
     */
    public Player makeMove(int row, int col) {
        if (gameOver || board.getPiece(row, col) != null)
            return null;

        board.placePiece(row, col, currentPlayer);

        Player ret = currentPlayer;

        if (board.has3InARow() || board.isFull()) {
            gameOver = true;
        } else {
            switchPlayer();
        }

        return ret;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    public String getCurrentPlayerName() {
        return currentPlayer.toString();
    }

    public boolean isDraw() {
        return board.isFull();
    }

    public boolean isWon() {
        return board.has3InARow();
    }
}
