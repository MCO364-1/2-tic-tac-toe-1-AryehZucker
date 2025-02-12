import java.awt.*;
import java.util.function.BiConsumer;

import javax.swing.*;

public class TicTacToeGUI {
    private static final int GRID_SIZE = 3;
    private JFrame window = new JFrame();
    private JButton newGameButton;
    private JLabel statusBar;
    private JButton[][] grid = new JButton[GRID_SIZE][GRID_SIZE];

    public TicTacToeGUI() {
        configureWindow();
        constructUI();
        window.setVisible(true);
    }

    private void configureWindow() {
        window.setSize(500, 500);
        window.setTitle("Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
    }

    private void constructUI() {
        addNewGameButton();
        addGrid();
        addStatusBar();
    }

    private void addNewGameButton() {
        newGameButton = new JButton("New Game");
        window.add(newGameButton, BorderLayout.NORTH);
    }

    private void addGrid() {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                JButton button = new JButton();
                grid[row][col] = button;
                gridPanel.add(button);
            }
        }
        window.add(gridPanel);
    }

    private void addStatusBar() {
        statusBar = new JLabel();
        window.add(statusBar, BorderLayout.SOUTH);
    }

    public void onNewGame(Runnable newGameTask) {
        newGameButton.addActionListener((e) -> newGameTask.run());
    }

    public void showCurrentPlayer(String player) {
        statusBar.setText(player + "'s turn");
    }

    public void clearGrid() {
        for (JButton[] buttonRow : grid) {
            for (JButton button : buttonRow) {
                button.setText("");
            }
        }
    }

    public void onMove(BiConsumer<Integer, Integer> moveTask) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                final int r = row, c = col;
                grid[row][col].addActionListener((e) -> moveTask.accept(r, c));
            }
        }
    }

    public void setSpace(int row, int col, String player) {
        grid[row][col].setText(player);
    }

    public void displayWinner(String player) {
        statusBar.setText(player + " wins!");
    }

    public void displayDraw() {
        statusBar.setText("Draw");
    }
}
