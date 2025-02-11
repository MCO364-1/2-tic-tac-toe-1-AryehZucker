public class Main {
    private static Board board;
    private static Piece currentPlayer;
    private static TicTacToeGUI gui;
    private static boolean gameOver;

    public static void main(String[] args) {
        gui = new TicTacToeGUI();
        gui.onNewGame(Main::reset);
        gui.onMove(Main::makeMove);

        reset();
    }

    private static void reset() {
        board = new Board();
        currentPlayer = Piece.X;
        gameOver = false;
        gui.displayCurrentPlayer(currentPlayer.toString());
        gui.clearGrid();
    }

    private static void makeMove(int row, int col) {
        if (gameOver || board.getPiece(row, col) != null)
            return;

        board.placePiece(row, col, currentPlayer);
        gui.setSpace(row, col, currentPlayer.toString());

        if (board.has3InARow()) {
            gameOver = true;
            gui.displayWinner(currentPlayer.toString());
        } else if (board.isFull()) {
            gameOver = true;
            gui.displayDraw();
        } else {
            switchPlayer();
            gui.displayCurrentPlayer(currentPlayer.toString());
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == Piece.X) ? Piece.O : Piece.X;
    }
}
