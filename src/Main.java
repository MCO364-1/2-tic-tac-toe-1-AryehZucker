public class Main {
    private static TicTacToeGUI gui;
    private static TicTacToeGame game;

    public static void main(String[] args) {
        game = new TicTacToeGame();
        gui = new TicTacToeGUI();

        gui.onNewGame(Main::start);
        gui.onMove(Main::move);

        start();
    }

    private static void start() {
        game = new TicTacToeGame();
        gui.displayCurrentPlayer(game.getCurrentPlayerName());
        gui.clearGrid();
    }

    private static void move(int row, int col) {
        Player player = game.makeMove(row, col);
        if (player != null)
            gui.setSpace(row, col, player.toString());
        updateStatusBar();
    }

    private static void updateStatusBar() {
        if (game.isWon()) {
            gui.displayWinner(game.getCurrentPlayerName());
        } else if (game.isDraw()) {
            gui.displayDraw();
        } else {
            gui.displayCurrentPlayer(game.getCurrentPlayerName());
        }
    }
}
