package tetra.logic;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetraTest {

    Tetra tetra;
    Matrix matrix;
    Piece piece;

    @Before
    public void setUp() {
        matrix = new Matrix(20, 10);
        piece = new Piece(new RandomTetromino(new Random()), matrix);
        tetra = new Tetra(matrix, piece);
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
