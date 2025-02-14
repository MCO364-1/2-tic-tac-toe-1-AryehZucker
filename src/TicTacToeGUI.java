import java.awt.*;
import java.util.function.BiConsumer;

import javax.swing.*;

public class TicTacToeGUI {
    private static final int GRID_SIZE = 3;

    private JFrame window = new JFrame();
    private JButton newGameButton;
    private JButton[][] grid = new JButton[GRID_SIZE][GRID_SIZE];
    private JLabel statusBar;

    public TicTacToeGUI() {
        configureWindow();
        constructUI();
        window.setVisible(true);
    }

    private void configureWindow() {
        window.setSize(500, 500);
        window.setTitle("Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void constructUI() {
        window.setLayout(new BorderLayout());
        addNewGameButton();
        addGrid();
        addStatusBar();
    }

    private void addNewGameButton() {
        newGameButton = new JButton("New Game");
        newGameButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
        newGameButton.setBackground(new Color(50, 50, 200));
        newGameButton.setForeground(new Color(220, 220, 220));
        window.add(newGameButton, BorderLayout.NORTH);
    }

    private void addGrid() {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                button.setBackground(new Color(40, 40, 60));
                button.setForeground(new Color(200, 200, 200));
                grid[row][col] = button;
                gridPanel.add(button);
            }
        }
        window.add(gridPanel, BorderLayout.CENTER);
    }

    private void addStatusBar() {
        statusBar = new JLabel();
        statusBar.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        statusBar.setOpaque(true);
        statusBar.setBackground(new Color(100, 255, 100));
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        window.add(statusBar, BorderLayout.SOUTH);
    }

    public void onNewGame(Runnable newGameTask) {
        newGameButton.addActionListener((e) -> newGameTask.run());
    }

    public void onMove(BiConsumer<Integer, Integer> moveTask) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                final int r = row, c = col;
                grid[row][col].addActionListener((e) -> moveTask.accept(r, c));
            }
        }
    }

    public void displayCurrentPlayer(String playerName) {
        statusBar.setText(playerName + "'s turn");
    }

    public void displayWinner(String playerName) {
        statusBar.setText(playerName + " wins!");
    }

    public void displayDraw() {
        statusBar.setText("Draw");
    }

    public void setSpace(int row, int col, String playerName) {
        grid[row][col].setText(playerName.toString());
    }

    public void clearGrid() {
        for (JButton[] row : grid) {
            for (JButton button : row) {
                button.setText("");
            }
        }
    }
}
