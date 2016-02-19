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

    @Test
    public void eachPieceOccursExactly4TimesInTheFirst28Pieces() {
        int[] count = new int[7];
        for (int i = 0; i < 28; i++) {
            count[random.nextTetromino().ordinal()]++;
        }

        assertEquals(4, count[0]);
        assertEquals(4, count[1]);
        assertEquals(4, count[2]);
        assertEquals(4, count[3]);
        assertEquals(4, count[4]);
        assertEquals(4, count[5]);
        assertEquals(4, count[6]);
    }

    @Test
    public void eachPieceOccursExactly4TimesInTheSecond28Pieces() {
        for (int i = 0; i < 28; i++) {
            random.nextTetromino();
        }

        int[] count = new int[7];
        for (int i = 0; i < 28; i++) {
            count[random.nextTetromino().ordinal()]++;
        }

        assertEquals(4, count[0]);
        assertEquals(4, count[1]);
        assertEquals(4, count[2]);
        assertEquals(4, count[3]);
        assertEquals(4, count[4]);
        assertEquals(4, count[5]);
        assertEquals(4, count[6]);
    }

    @Test
    public void eachPieceOccursExactly40TimesInTheFirst280Pieces() {
        int[] count = new int[7];
        for (int i = 0; i < 280; i++) {
            count[random.nextTetromino().ordinal()]++;
        }

        assertEquals(40, count[0]);
        assertEquals(40, count[1]);
        assertEquals(40, count[2]);
        assertEquals(40, count[3]);
        assertEquals(40, count[4]);
        assertEquals(40, count[5]);
        assertEquals(40, count[6]);
    }

}
