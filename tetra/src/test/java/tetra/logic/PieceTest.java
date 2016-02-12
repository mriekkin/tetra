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
        RandomIntStub random = new RandomIntStub(new int[]{first.ordinal()});
        return new Piece(new RandomTetromino(random), matrix);
    }

    @Test
    public void constructorSetsTetrominoCorrectly() {
        assertEquals(Tetromino.T, createPiece(Tetromino.T).getTetromino());
    }

}
