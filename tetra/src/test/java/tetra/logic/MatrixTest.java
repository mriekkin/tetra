package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    Matrix matrix;
    Block block;

    @Before
    public void setUp() {
        matrix = new Matrix(20, 10);
        block = new Block(1010);
    }

    @Test
    public void constructorSetsHeightCorrectly() {
        assertEquals(20, matrix.getRows());
    }

    @Test
    public void constructorSetsWidthCorrectly() {
        assertEquals(10, matrix.getCols());
    }

    @Test
    public void constructorCreatesAnEmptyMatrix() {
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                assertNull(matrix.getBlock(x, y));
            }
        }
    }

    @Test
    public void isOccupiedReturnsFalseForNegativeCoordinates() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 20, block);
        assertEquals(false, fullMatrix.isOccupied(-1, -1));
        assertEquals(false, fullMatrix.isOccupied(-1, 0));
        assertEquals(false, fullMatrix.isOccupied(0, -1));
    }

    @Test
    public void isOccupiedReturnsFalseForTooBigCoordinates() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 20, block);
        assertEquals(false, fullMatrix.isOccupied(10, 20));
        assertEquals(false, fullMatrix.isOccupied(10, 0));
        assertEquals(false, fullMatrix.isOccupied(0, 20));
    }

    @Test
    public void isOccupiedReturnsTrueForOccupiedInteriorCoordinates() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 20, block);
        for (int y = 0; y < fullMatrix.getRows(); y++) {
            for (int x = 0; x < fullMatrix.getCols(); x++) {
                assertEquals(true, fullMatrix.isOccupied(x, y));
            }
        }
    }

    @Test
    public void getBlockReturnsNullForNegativeCoordinates() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 20, block);
        assertEquals(null, fullMatrix.getBlock(-1, -1));
        assertEquals(null, fullMatrix.getBlock(-1, 0));
        assertEquals(null, fullMatrix.getBlock(0, -1));
    }

    @Test
    public void getBlockReturnsNullForTooBigCoordinates() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 20, block);
        assertEquals(null, fullMatrix.getBlock(10, 20));
        assertEquals(null, fullMatrix.getBlock(10, 0));
        assertEquals(null, fullMatrix.getBlock(0, 20));
    }

    @Test
    public void getBlockReturnsTheRightBlockForOccupiedInteriorCoordinates() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 20, block);
        for (int y = 0; y < fullMatrix.getRows(); y++) {
            for (int x = 0; x < fullMatrix.getCols(); x++) {
                assertEquals(block, fullMatrix.getBlock(x, y));
            }
        }
    }

    @Test
    public void isOutOfBoundsReturnsTrueForNegativeCoordinates() {
        assertEquals(true, matrix.isOutOfBounds(-1, -1));
        assertEquals(true, matrix.isOutOfBounds(-1, 0));
        assertEquals(true, matrix.isOutOfBounds(0, -1));
    }

    @Test
    public void isOutOfBoundsReturnsTrueForTooBigCoordinates() {
        assertEquals(true, matrix.isOutOfBounds(10, 20));
        assertEquals(true, matrix.isOutOfBounds(10, 0));
        assertEquals(true, matrix.isOutOfBounds(0, 20));
    }

    @Test
    public void isOutOfBoundsReturnsFalseForCornerCoordinates() {
        assertEquals(false, matrix.isOutOfBounds(0, 0));
        assertEquals(false, matrix.isOutOfBounds(9, 0));
        assertEquals(false, matrix.isOutOfBounds(0, 19));
        assertEquals(false, matrix.isOutOfBounds(9, 19));
    }

    @Test
    public void isOutOfBoundsReturnsFalseForInteriorCoordinates() {
        assertEquals(false, matrix.isOutOfBounds(1, 1));
        assertEquals(false, matrix.isOutOfBounds(5, 15));
        assertEquals(false, matrix.isOutOfBounds(8, 18));
    }

    @Test
    public void setBlockDoesNothingForInvalidCoordinates() {
        matrix.setBlock(-1, -1, block);
        matrix.setBlock(10, 20, block);
        assertEquals(null, matrix.getBlock(0, 0));
        assertEquals(null, matrix.getBlock(9, 19));
    }

    @Test
    public void setBlockWorks() {
        matrix.setBlock(5, 15, block);
        assertEquals(block, matrix.getBlock(5, 15));
    }

    @Test
    public void addPieceWorks() {
        Piece piece = new Piece(Tetromino.I, matrix);
        matrix.addPiece(piece);
        assertEquals(true, matrix.isOccupied(3, 1));
        assertEquals(true, matrix.isOccupied(4, 1));
        assertEquals(true, matrix.isOccupied(5, 1));
        assertEquals(true, matrix.isOccupied(6, 1));
    }

    @Test
    public void addPieceWorksCompleteTest() {
        Piece piece = new Piece(Tetromino.I, matrix);
        matrix.addPiece(piece);
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                assertEquals(piece.getBlock(x, y), matrix.getBlock(x, y));
            }
        }
    }

    @Test
    public void addPieceWorksCompleteTestRotated() {
        Piece piece = new Piece(Tetromino.I, matrix);
        piece.rotate(true);
        matrix.addPiece(piece);
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                assertEquals(piece.getBlock(x, y), matrix.getBlock(x, y));
            }
        }
    }

    @Test
    public void addPieceDoesNotAddAnythignExtra() {
        Piece piece = new Piece(Tetromino.I, matrix);
        matrix.addPiece(piece);
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                if (y != 1 || (x < 3 || x > 6)) {
                    assertEquals(false, matrix.isOccupied(x, y));
                }
            }
        }
    }

    @Test
    public void clearCompleteLinesReturnsZeroForEmptyMatrix() {
        assertEquals(0, matrix.clearCompleteLines(0, 19));
    }

    @Test
    public void clearCompleteLinesReturnsTheNumberOfRowsForFullMatrix() {
        assertEquals(10, MatrixHelper.getFullMatrix(10, 10, block).clearCompleteLines(0, 9));
        assertEquals(20, MatrixHelper.getFullMatrix(10, 20, block).clearCompleteLines(0, 19));
        assertEquals(22, MatrixHelper.getFullMatrix(10, 22, block).clearCompleteLines(0, 21));
    }

    @Test
    public void clearCompleteLinesReturnsTheCorrectAnswerForPartiallyFilledMatrix() {
        MatrixHelper.fillRow(matrix, 10, block);
        MatrixHelper.fillRow(matrix, 11, block);
        assertEquals(2, matrix.clearCompleteLines(0, 19));
    }

    @Test
    public void clearCompleteLinesOnlyClearsWithinTheSpecifiedRange() {
        assertEquals(1, MatrixHelper.getFullMatrix(10, 10, block).clearCompleteLines(0, 0));
        assertEquals(1, MatrixHelper.getFullMatrix(11, 11, block).clearCompleteLines(10, 10));
        assertEquals(1, MatrixHelper.getFullMatrix(22, 22, block).clearCompleteLines(21, 21));
        assertEquals(3, MatrixHelper.getFullMatrix(10, 20, block).clearCompleteLines(11, 13));
        assertEquals(4, MatrixHelper.getFullMatrix(10, 22, block).clearCompleteLines(11, 14));
    }

    @Test
    public void clearCompleteLinesIgnoresInvalidYCoordinates() {
        assertEquals(22, MatrixHelper.getFullMatrix(10, 22, block).clearCompleteLines(-30, 30));
        assertEquals(0, MatrixHelper.getFullMatrix(10, 22, block).clearCompleteLines(-30, -1));
        assertEquals(0, MatrixHelper.getFullMatrix(10, 22, block).clearCompleteLines(22, 30));
    }

}
