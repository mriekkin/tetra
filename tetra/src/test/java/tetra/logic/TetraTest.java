package tetra.logic;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetraTest {

    Tetra tetra;
    Matrix matrix;
    Piece piece;
    LineClearer lineClearer;
    RandomTetromino random;

    @Before
    public void setUp() {
        random = new RandomTetromino(new Random());
        Tetromino first = random.nextTetromino();

        matrix = new Matrix(20, 10);
        piece = new Piece(first, matrix);
        lineClearer = new LineClearer(matrix);
        tetra = new Tetra(matrix, piece, random);
    }

    @Test
    public void constructorSetsMatrixCorrectly() {
        assertTrue(tetra.getMatrix() == matrix);
    }

    @Test
    public void constructorSetsPieceCorrectly() {
        assertTrue(tetra.getPiece() == piece);
    }

}
