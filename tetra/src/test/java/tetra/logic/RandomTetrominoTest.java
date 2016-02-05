package tetra.logic;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomTetrominoTest {

    RandomTetromino random;

    @Before
    public void setUp() {
        random = new RandomTetromino(new Random());
    }

    @Test
    public void createsBagCorrectly() {
        int[] integers = new int[]{6, 5, 4, 3, 2, 1, 0};
        random = new RandomTetromino(new RandomIntStub(integers));

        assertEquals(Tetromino.Z, random.nextTetromino());
        assertEquals(Tetromino.T, random.nextTetromino());
        assertEquals(Tetromino.S, random.nextTetromino());
        assertEquals(Tetromino.O, random.nextTetromino());
        assertEquals(Tetromino.L, random.nextTetromino());
        assertEquals(Tetromino.J, random.nextTetromino());
        assertEquals(Tetromino.I, random.nextTetromino());
    }

    @Test
    public void resetsBagToInitialStateAfter28Tetrominoes() {
        int[] integers = new int[]{6, 5, 4, 3, 2, 1, 0};
        random = new RandomTetromino(new RandomIntStub(integers));

        for (int i = 0; i < 28; i++) {
            random.nextTetromino();
        }

        assertEquals(Tetromino.Z, random.nextTetromino());
        assertEquals(Tetromino.T, random.nextTetromino());
        assertEquals(Tetromino.S, random.nextTetromino());
        assertEquals(Tetromino.O, random.nextTetromino());
        assertEquals(Tetromino.L, random.nextTetromino());
        assertEquals(Tetromino.J, random.nextTetromino());
        assertEquals(Tetromino.I, random.nextTetromino());
    }

    @Test
    public void canGenerateALargeNumberOfTetrominoes() {
        for (int i = 0; i < 100; i++) {
            assertTrue(random.nextTetromino() != null);
        }
    }

}
