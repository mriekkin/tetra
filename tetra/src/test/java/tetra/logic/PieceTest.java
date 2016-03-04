package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceTest {

    Piece piece;
    Matrix matrix;

    @Before
    public void setUp() {
        matrix = new Matrix(22, 10);
        piece = new Piece(Tetromino.I, matrix);
    }

    @Test
    public void constructorSetsTetrominoCorrectly() {
        assertEquals(Tetromino.I, piece.getTetromino());
    }

    @Test
    public void constructorSetsInitialPositionCorrectly() {
        assertEquals(3, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void constructorSetsInitialOrientationUp() {
        assertEquals(Direction.UP, piece.getOrientation());
    }

    @Test
    public void getBlockUsesMatrixCoordinatesNotLocalCoordinates() {
        Block block = piece.getBlock(3, 1);
        assertTrue(block != null);
        assertEquals(null, piece.getBlock(3, 21));
        while (piece.canMove(Direction.DOWN)) {
            piece.move(Direction.DOWN);
        }
        assertEquals(null, piece.getBlock(3, 1));
        assertEquals(block, piece.getBlock(3, 21));
    }

    @Test
    public void lockAddsPieceToMatrix() {
        piece.lock();
        assertEquals(true, matrix.isOccupied(3, 1));
        assertEquals(true, matrix.isOccupied(4, 1));
        assertEquals(true, matrix.isOccupied(5, 1));
        assertEquals(true, matrix.isOccupied(6, 1));
    }

    @Test
    public void respawnSetsNextTetrominoCorrectly() {
        piece.respawn(Tetromino.J);
        assertEquals(Tetromino.J, piece.getTetromino());
        piece.respawn(Tetromino.T);
        assertEquals(Tetromino.T, piece.getTetromino());
    }

    @Test
    public void respawnMovesPieceToInitialPosition() {
        piece.move(Direction.DOWN);
        piece.move(Direction.DOWN);
        piece.move(Direction.DOWN);
        piece.move(Direction.RIGHT);
        piece.move(Direction.RIGHT);
        piece.move(Direction.RIGHT);
        assertTrue(piece.getX() != 3);
        assertTrue(piece.getY() != 0);
        piece.respawn(Tetromino.T);
        assertEquals(3, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void respawnReturnsTrueIfNextPieceDoesNotIntersectWithMatrix() {
        assertEquals(true, piece.respawn(Tetromino.I));
    }

    @Test
    public void respawnReturnsFalseIfNextPieceIntersectsWithMatrix() {
        MatrixHelper.fillRow(matrix, 1, new Block(1010));
        assertEquals(false, piece.respawn(Tetromino.I));
    }

    @Test
    public void getWidthReturns4() {
        assertEquals(4, piece.getWidth());
    }

    @Test
    public void getHeightReturns4() {
        assertEquals(4, piece.getHeight());
    }

}
