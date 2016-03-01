package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixGetAndSetBlocksTest {

    Matrix matrix;
    Block block;

    @Before
    public void setUp() {
        matrix = new Matrix(20, 10);
        block = new Block(1010);
    }

    @Test
    public void getBlockReturnsNullForNegativeCoordinates() {
        assertNull(matrix.getBlock(-1, -1));
        assertNull(matrix.getBlock(-1, 0));
        assertNull(matrix.getBlock(0, -1));
    }

    @Test
    public void getBlockReturnsNullForNegativeCoordinatesEvenForAFullMatrix() {
        matrix = MatrixHelper.getFullMatrix(10, 20, block);
        assertNull(matrix.getBlock(-1, -1));
        assertNull(matrix.getBlock(-1, 0));
        assertNull(matrix.getBlock(0, -1));
    }

    @Test
    public void getBlockReturnsNullForTooLargeCoordinates() {
        assertNull(matrix.getBlock(matrix.getCols(), matrix.getRows()));
        assertNull(matrix.getBlock(matrix.getCols(), 0));
        assertNull(matrix.getBlock(0, matrix.getRows()));
        assertNull(matrix.getBlock(100, 100));
    }

    @Test
    public void getBlockReturnsNullForTooLargeCoordinatesEvenForAFullMatrix() {
        matrix = MatrixHelper.getFullMatrix(10, 20, block);
        assertNull(matrix.getBlock(matrix.getCols(), matrix.getRows()));
        assertNull(matrix.getBlock(matrix.getCols(), 0));
        assertNull(matrix.getBlock(0, matrix.getRows()));
        assertNull(matrix.getBlock(100, 100));
    }

    @Test
    public void setBlockWorksCorrectly() {
        matrix.setBlock(5, 10, block);
        assertEquals(block, matrix.getBlock(5, 10));
    }

    @Test
    public void setBlockWorksForTopLeftCorner() {
        matrix.setBlock(0, 0, block);
        assertEquals(block, matrix.getBlock(0, 0));
    }

    @Test
    public void setBlockWorksForTopRightCorner() {
        matrix.setBlock(9, 0, block);
        assertEquals(block, matrix.getBlock(9, 0));
    }

    @Test
    public void setBlockWorksForBottomLeftCorner() {
        matrix.setBlock(0, 19, block);
        assertEquals(block, matrix.getBlock(0, 19));
    }

    @Test
    public void setBlockWorksForBottomRightCorner() {
        matrix.setBlock(9, 19, block);
        assertEquals(block, matrix.getBlock(9, 19));
    }

    @Test
    public void setBlockIsAbleToFillAllCells() {
        matrix = MatrixHelper.getFullMatrix(10, 20, block);
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                assertEquals(block, matrix.getBlock(x, y));
            }
        }
    }

}
