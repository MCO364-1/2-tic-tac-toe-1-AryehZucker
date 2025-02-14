import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeGameTest {
    private TicTacToeGame game = new TicTacToeGame();

    @Test
    public void testMakeMoveOnPiece() {
        game.makeMove(0, 0);
        assertNull(game.makeMove(0, 0));
    }

    @Test
    public void testMakeMoveReturnPlayer() {
        assertEquals(Player.X, game.makeMove(0, 0));
        assertEquals(Player.O, game.makeMove(1, 1));
        assertEquals(Player.X, game.makeMove(2, 2));
    }

    @Test
    public void testSwitchPlayer() {
        assertEquals("X", game.getCurrentPlayerName());
        game.makeMove(0, 0);
        assertEquals("O", game.getCurrentPlayerName());
    }
}
