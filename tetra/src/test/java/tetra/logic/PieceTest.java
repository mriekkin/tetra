package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PieceTest {

    Piece piece;
    Matrix matrix;

    @Before
    public void setUp() {
        matrix = new Matrix(20, 10);
    }

    private Piece createPiece(Tetromino first) {
        return new Piece(first, matrix);
    }

    @Test
    public void constructorSetsTetrominoCorrectly() {
        assertEquals(Tetromino.T, createPiece(Tetromino.T).getTetromino());
    }

}
