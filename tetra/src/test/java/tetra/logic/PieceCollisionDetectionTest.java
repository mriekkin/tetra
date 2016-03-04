package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceCollisionDetectionTest {

    Piece piece;
    Matrix matrix;

    @Before
    public void setUp() {
        matrix = new Matrix(22, 10);
        piece = new Piece(Tetromino.I, matrix);
    }

    @Test
    public void testCollisionWorksCorrectlyInDifficultCase() {
        piece = new Piece(Tetromino.T, matrix);
        MatrixHelper.fillRow(matrix, 0, new Block(1010));
        MatrixHelper.fillRow(matrix, 1, new Block(1010));
        MatrixHelper.fillRow(matrix, 2, new Block(1010));
        matrix.setBlock(4, 0, null);
        matrix.setBlock(3, 1, null);
        matrix.setBlock(4, 1, null);
        matrix.setBlock(5, 1, null);
        matrix.setBlock(4, 2, null);
        for (int i = 0; i < 4; i++) {
            assertEquals(true, piece.canRotate(true));
            piece.rotate(true);
        }
    }

    @Test
    public void testCollisionIncludesTheFirstRow() {
        piece.rotate(true);
        matrix.setBlock(4, 0, new Block(1010));
        assertFalse(piece.canMove(Direction.LEFT));
    }

    @Test
    public void testCollisionIncludesTheLastRow() {
        piece.rotate(true);
        matrix.setBlock(4, 3, new Block(1010));
        assertFalse(piece.canMove(Direction.LEFT));
    }

    @Test
    public void testCollisionIncludesTheFirstColumn() {
        matrix.setBlock(3, 2, new Block(1010));
        assertFalse(piece.canMove(Direction.DOWN));
    }

    @Test
    public void testCollisionIncludesTheLastColumn() {
        matrix.setBlock(6, 2, new Block(1010));
        assertFalse(piece.canMove(Direction.DOWN));
    }

}
