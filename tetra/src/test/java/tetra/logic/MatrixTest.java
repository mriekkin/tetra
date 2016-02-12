package tetra.logic;

import java.util.Random;
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

    private Piece createPiece(Tetromino first) {
        RandomIntStub random = new RandomIntStub(new int[]{first.ordinal()});
        return new Piece(new RandomTetromino(random), matrix);
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
    public void addPieceWorks() {

    }

    @Test
    public void isOccupiedWorks() {

    }

}
