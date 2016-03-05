package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineClearerTest {

    private Block block;
    private Matrix matrix;
    private LineClearer lineClearer;

    @Before
    public void setUp() {
        block = new Block(1010);
        matrix = new Matrix(22, 10);
        lineClearer = new LineClearer(matrix);
    }

    @Test
    public void isCompleteLineReturnsTrueForCompleteLines() {
        MatrixHelper.fillRow(matrix, 0, block);
        MatrixHelper.fillRow(matrix, 10, block);
        MatrixHelper.fillRow(matrix, 21, block);
        assertEquals(true, lineClearer.isCompleteLine(0));
        assertEquals(true, lineClearer.isCompleteLine(10));
        assertEquals(true, lineClearer.isCompleteLine(21));
    }

    @Test
    public void isCompleteLineReturnsFalseForEmptyLines() {
        MatrixHelper.fillRow(matrix, 0, block);
        MatrixHelper.fillRow(matrix, 10, block);
        MatrixHelper.fillRow(matrix, 21, block);
        assertEquals(false, lineClearer.isCompleteLine(1));
        assertEquals(false, lineClearer.isCompleteLine(9));
        assertEquals(false, lineClearer.isCompleteLine(11));
        assertEquals(false, lineClearer.isCompleteLine(20));
    }

    @Test
    public void isCompleteLineReturnsFalseIfRowLacksOneBlock() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 22, block);
        lineClearer = new LineClearer(fullMatrix);
        fullMatrix.setBlock(0, 0, null);
        fullMatrix.setBlock(5, 10, null);
        fullMatrix.setBlock(9, 21, null);
        assertEquals(false, lineClearer.isCompleteLine(0));
        assertEquals(false, lineClearer.isCompleteLine(10));
        assertEquals(false, lineClearer.isCompleteLine(21));
    }

    @Test
    public void isEmptyLineReturnsTrueForEmptyLines() {
        MatrixHelper.fillRow(matrix, 0, block);
        MatrixHelper.fillRow(matrix, 10, block);
        MatrixHelper.fillRow(matrix, 21, block);
        assertEquals(true, lineClearer.isEmptyLine(1));
        assertEquals(true, lineClearer.isEmptyLine(9));
        assertEquals(true, lineClearer.isEmptyLine(11));
        assertEquals(true, lineClearer.isEmptyLine(20));
    }

    @Test
    public void isEmptyLineReturnsFalseForLinesWhichHaveOneBlock() {
        matrix.setBlock(0, 0, block);
        matrix.setBlock(5, 10, block);
        matrix.setBlock(9, 21, block);
        assertEquals(false, lineClearer.isEmptyLine(0));
        assertEquals(false, lineClearer.isEmptyLine(10));
        assertEquals(false, lineClearer.isEmptyLine(21));
    }

    @Test
    public void findFirstCompleteLineReturnsMinusOneIfMinGreaterThanMax() {
        MatrixHelper.fillRow(matrix, 1, block);
        assertEquals(-1, lineClearer.findFirstCompleteLine(11, 10));
    }

    @Test
    public void findLastCompleteLineReturnsMinusOneIfMinGreaterThanMax() {
        MatrixHelper.fillRow(matrix, 1, block);
        assertEquals(-1, lineClearer.findLastCompleteLine(11, 10));
    }

    @Test
    public void findFirstCompleteLinesReturnsMinusOneForAnEmptyMatrix() {
        assertEquals(-1, lineClearer.findFirstCompleteLine(0, 21));
    }

    @Test
    public void findLastCompleteLinesReturnsMinusOneForAnEmptyMatrix() {
        assertEquals(-1, lineClearer.findLastCompleteLine(0, 21));
    }

    @Test
    public void clearLinesDoesNothingIfFirstGreaterThanLast() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 22, block);
        lineClearer = new LineClearer(fullMatrix);
        lineClearer.clearLines(11, 10);
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 0, 21));
    }

    @Test
    public void clearLinesSetsSpecifiedLinesToNull() {
        Matrix fullMatrix = MatrixHelper.getFullMatrix(10, 22, block);
        lineClearer = new LineClearer(fullMatrix);
        lineClearer.clearLines(10, 11);
        assertEquals(true, lineClearer.isEmptyLine(10));
        assertEquals(true, lineClearer.isEmptyLine(11));
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 0, 9));
        assertEquals(true, MatrixHelper.areCompleteLines(lineClearer, 12, 21));
    }

    @Test
    public void clearCompleteLinesAndShiftWorks() {
        MatrixHelper.fillRow(matrix, 19, block);
        MatrixHelper.fillRow(matrix, 20, block);
        MatrixHelper.fillRow(matrix, 21, block);
        int rows = lineClearer.clearCompleteLinesAndShiftDown(20, 21);
        assertEquals(2, rows);
        assertEquals(true, lineClearer.isEmptyLine(19));
        assertEquals(true, lineClearer.isEmptyLine(20));
        assertEquals(true, lineClearer.isCompleteLine(21));
    }

    @Test
    public void clearCompleteLinesAndShiftWorksOnTheTopRow() {
        MatrixHelper.fillRow(matrix, 0, block);
        MatrixHelper.fillRow(matrix, 1, block);
        MatrixHelper.fillRow(matrix, 2, block);
        MatrixHelper.fillRow(matrix, 3, block);
        MatrixHelper.fillRow(matrix, 4, block);
        lineClearer.clearCompleteLinesAndShiftDown(0, 2);
        assertEquals(true, lineClearer.isEmptyLine(0));
        assertEquals(true, lineClearer.isEmptyLine(1));
        assertEquals(true, lineClearer.isEmptyLine(2));
        assertEquals(true, lineClearer.isCompleteLine(3));
        assertEquals(true, lineClearer.isCompleteLine(4));
    }

}
