package tetra.logic;

import java.awt.Component;
import javax.swing.JPanel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetraTimerTest {

    Tetra game;
    Matrix matrix;
    Piece piece;
    LineClearer lineClearer;
    RandomTetromino random;

    @Before
    public void setUp() {
        int[] integers = new int[]{0, 1, 2, 3, 4, 5, 6};
        random = new RandomTetromino(new RandomIntStub(integers));
        matrix = new Matrix(50, 10);
        piece = new Piece(random.nextTetromino(), matrix);
        lineClearer = new LineClearer(matrix);
        game = new Tetra(matrix, piece, random);
    }
    
    @Test
    public void timerDelayStartsAt600() {
        assertEquals(600, game.getTimerDelay());
    }

}
