package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineClearerShiftRowsTest {

    private Block block;
    private Matrix fullMatrix;
    private LineClearer lineClearer;

    @Before
    public void setUp() {
        block = new Block(1010);
        fullMatrix = MatrixHelper.getFullMatrix(10, 22, block);
        lineClearer = new LineClearer(fullMatrix);
    }

    @Test
    public void shiftRowsDownDoesNothingIfLastRowToMoveIsNegative() {
        lineClearer.shiftRowsDown(-1, 1);
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 0, 21));
    }

    @Test
    public void shiftRowsDownDoesNothingIfLastRowToMoveIsTooBig() {
        lineClearer.shiftRowsDown(21, 1);
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 0, 21));
    }

    @Test
    public void shiftRowsDownDoesNothingIfHowMuchIsNegative() {
        lineClearer.shiftRowsDown(1, -1);
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 0, 21));
    }

    @Test
    public void shiftRowsDownDoesNothingIfHowMuchIsZero() {
        lineClearer.shiftRowsDown(1, 0);
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 0, 21));
    }

    @Test
    public void shiftRowsDownDoesNothingIfHowMuchIsTooBig() {
        lineClearer.shiftRowsDown(11, 11);
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 0, 21));
    }

    @Test
    public void shiftRowsDownWorksForCornerCase() {
        lineClearer.shiftRowsDown(20, 1);
        assertEquals(true, lineClearer.isEmptyLine(0));
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 1, 21));
    }

    @Test
    public void shiftRowsDownByOneCreatesOneEmptyLineOnTop() {
        lineClearer.shiftRowsDown(10, 1);
        assertEquals(true, lineClearer.isEmptyLine(0));
    }

    @Test
    public void shiftRowsDownByFourCreatesFourEmptyLinesOnTop() {
        lineClearer.shiftRowsDown(10, 4);
        assertEquals(true, lineClearer.isEmptyLine(0));
        assertEquals(true, lineClearer.isEmptyLine(1));
        assertEquals(true, lineClearer.isEmptyLine(2));
        assertEquals(true, lineClearer.isEmptyLine(3));
    }

    @Test
    public void shiftRowsDownWorksForTheFirstLine() {
        Matrix matrix = new Matrix(22, 10);
        lineClearer = new LineClearer(matrix);
        MatrixHelper.fillRow(matrix, 0, block);
        lineClearer.shiftRowsDown(0, 1);
        for (int y = 0; y < matrix.getRows(); y++) {
            assertEquals("row y=" + y, y == 1, lineClearer.isCompleteLine(y));
        }
    }

    @Test
    public void shiftRowsDownWorksForContinuousBlocks() {
        Matrix matrix = new Matrix(22, 10);
        lineClearer = new LineClearer(matrix);
        MatrixHelper.fillRow(matrix, 10, block);
        MatrixHelper.fillRow(matrix, 11, block);
        MatrixHelper.fillRow(matrix, 12, block);
        lineClearer.shiftRowsDown(12, 3);
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                assertEquals("row y=" + y, y >= 13 && y <= 15, matrix.isOccupied(x, y));
            }
        }
    }

    @Test
    public void shiftRowsDownWorksForDiscontinuousBlocks() {
        Matrix matrix = new Matrix(22, 10);
        lineClearer = new LineClearer(matrix);
        MatrixHelper.fillRow(matrix, 0, block);
        MatrixHelper.fillRow(matrix, 5, block);
        MatrixHelper.fillRow(matrix, 10, block);
        lineClearer.shiftRowsDown(10, 5);
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                assertEquals("row y=" + y, y == 5 || y == 10 || y == 15, matrix.isOccupied(x, y));
            }
        }
    }

}
