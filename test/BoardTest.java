import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board = new Board();

    @Test
    public void testEmptyBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertNull(board.getPiece(i, j));
            }
        }
    }

    @Test
    public void testPlaceAndGetPiece() {
        board.placePiece(0, 0, Player.X);
        assertEquals(board.getPiece(0, 0), Player.X);
    }

    @Test
    public void testGetPieceOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> board.getPiece(0, 3));
        assertThrows(IllegalArgumentException.class, () -> board.getPiece(-1, 2));
    }

    @Test
    public void testPlacePieceOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> board.placePiece(0, 3, Player.X));
        assertThrows(IllegalArgumentException.class, () -> board.placePiece(-1, 2, Player.X));
    }

    @Test
    public void testPlacePieceOnExistingPiece() {
        board.placePiece(0, 0, Player.X);
        assertThrows(IllegalArgumentException.class, () -> board.placePiece(0, 0, Player.X));
        assertThrows(IllegalArgumentException.class, () -> board.placePiece(0, 0, Player.O));
    }

    @Test
    public void testIsFull() {
        assertFalse(board.isFull());
        fillBoard();
        assertTrue(board.isFull());
    }

    private void fillBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.placePiece(i, j, Player.X);
            }
        }
    }

    @Test
    public void testHas3InARowNone() {
        board.placePiece(1, 1, Player.X);
        board.placePiece(0, 1, Player.O);
        assertFalse(board.has3InARow());
    }

    @Test
    public void testHas3InARowDifferent() {
        board.placePiece(0, 0, Player.O);
        board.placePiece(0, 1, Player.X);
        board.placePiece(0, 2, Player.O);
        assertFalse(board.has3InARow());
    }

    @Test
    public void testHas3InARowHorizontal() {
        board.placePiece(0, 0, Player.X);
        board.placePiece(0, 1, Player.X);
        board.placePiece(0, 2, Player.X);
        assertTrue(board.has3InARow());
    }

    @Test
    public void testHas3InARowVertical() {
        board.placePiece(0, 0, Player.X);
        board.placePiece(1, 0, Player.X);
        board.placePiece(2, 0, Player.X);
        assertTrue(board.has3InARow());
    }

    @Test
    public void testHas3InARowDiagonal1() {
        board.placePiece(0, 0, Player.X);
        board.placePiece(1, 1, Player.X);
        board.placePiece(2, 2, Player.X);
        assertTrue(board.has3InARow());
    }

    @Test
    public void testHas3InARowDiagonal2() {
        board.placePiece(0, 2, Player.X);
        board.placePiece(1, 1, Player.X);
        board.placePiece(2, 0, Player.X);
        assertTrue(board.has3InARow());
    }
}
