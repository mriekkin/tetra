package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceMovementTest {

    Piece piece;
    Matrix matrix;

    @Before
    public void setUp() {
        matrix = new Matrix(22, 10);
        piece = new Piece(Tetromino.I, matrix);
    }

    @Test
    public void canMoveReturnsTrueIfCanMove() {
        assertEquals(true, piece.canMove(Direction.LEFT));
        assertEquals(true, piece.canMove(Direction.RIGHT));
    }

    @Test
    public void canMoveReturnsFalseIfCannotMoveLeft() {
        piece.move(Direction.LEFT);
        piece.move(Direction.LEFT);
        assertEquals(true, piece.canMove(Direction.LEFT));
        piece.move(Direction.LEFT);
        assertEquals(false, piece.canMove(Direction.LEFT));
    }

    @Test
    public void canMoveReturnsFalseIfCannotMoveRight() {
        piece.move(Direction.RIGHT);
        piece.move(Direction.RIGHT);
        assertEquals(true, piece.canMove(Direction.RIGHT));
        piece.move(Direction.RIGHT);
        assertEquals(false, piece.canMove(Direction.RIGHT));
    }

    @Test
    public void moveUpdatesCoordinatesIfCanMove() {
        assertEquals(3, piece.getX());
        assertEquals(0, piece.getY());
        piece.move(Direction.DOWN);
        assertEquals(3, piece.getX());
        assertEquals(1, piece.getY());
        piece.move(Direction.RIGHT);
        assertEquals(4, piece.getX());
        assertEquals(1, piece.getY());
    }

    @Test
    public void moveDoesNotChangeCoordinatesIfCannotMove() {
        piece.move(Direction.LEFT);
        piece.move(Direction.LEFT);
        piece.move(Direction.LEFT);
        assertEquals(0, piece.getX());
        piece.move(Direction.LEFT);
        assertEquals(0, piece.getX());
        assertEquals(0, piece.getY());
    }

    @Test
    public void coordinatesCanBecomeNegativeIfTetrominoHasOneEmptyColumnOnTheLeft() {
        piece = new Piece(Tetromino.O, matrix);
        while (piece.canMove(Direction.LEFT)) {
            piece.move(Direction.LEFT);
        }
        assertEquals(-1, piece.getX());
    }

    @Test
    public void coordinatesCanExceedTheRightBorderIfTetrominoHasEmptyColumnsOnTheRight() {
        piece = new Piece(Tetromino.O, matrix);
        while (piece.canMove(Direction.RIGHT)) {
            piece.move(Direction.RIGHT);
        }
        assertEquals((10 - 4) + 1, piece.getX());
    }

    @Test
    public void coordinatesCanBecomeNegativeIfTetrominoHasOneEmptyRowOnTheTop() {
        while (piece.canMove(Direction.UP)) {
            piece.move(Direction.UP);
        }
        assertEquals(-1, piece.getY());
    }

    @Test
    public void coordinatesCanExceedTheLowerBorderIfTetrominoHasEmptyRowsOnTheBottom() {
        while (piece.canMove(Direction.DOWN)) {
            piece.move(Direction.DOWN);
        }
        assertEquals((22 - 4) + 2, piece.getY());
    }

    @Test
    public void canRotateReturnsTrueIfCanRotate() {
        assertEquals(true, piece.canRotate(true));
        assertEquals(true, piece.canRotate(false));
    }

    @Test
    public void canRotateReturnsFalseIfCannotRotate() {
        piece.move(Direction.UP);
        assertEquals(false, piece.canRotate(true));
        assertEquals(false, piece.canRotate(false));
    }

    @Test
    public void rotateUpdatesOrientationIfCanRotateClockwise() {
        assertEquals(Direction.UP, piece.getOrientation());
        piece.rotate(true);
        assertEquals(Direction.RIGHT, piece.getOrientation());
        piece.rotate(true);
        assertEquals(Direction.DOWN, piece.getOrientation());
        piece.rotate(true);
        assertEquals(Direction.LEFT, piece.getOrientation());
        piece.rotate(true);
        assertEquals(Direction.UP, piece.getOrientation());
    }

    @Test
    public void rotateUpdatesOrientationIfCanRotateCounterclockwise() {
        assertEquals(Direction.UP, piece.getOrientation());
        piece.rotate(false);
        assertEquals(Direction.LEFT, piece.getOrientation());
        piece.rotate(false);
        assertEquals(Direction.DOWN, piece.getOrientation());
        piece.rotate(false);
        assertEquals(Direction.RIGHT, piece.getOrientation());
        piece.rotate(false);
        assertEquals(Direction.UP, piece.getOrientation());
    }

    @Test
    public void rotateDoesNotChangeOrientationIfCannotRotate() {
        piece.move(Direction.UP);
        assertEquals(false, piece.canRotate(true));
        assertEquals(Direction.UP, piece.getOrientation());
        piece.rotate(true);
        assertEquals(Direction.UP, piece.getOrientation());
        piece.rotate(false);
        assertEquals(Direction.UP, piece.getOrientation());
    }

}
