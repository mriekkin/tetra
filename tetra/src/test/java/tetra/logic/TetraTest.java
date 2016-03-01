package tetra.logic;

import java.awt.Component;
import javax.swing.JPanel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetraTest {

    Tetra game;
    Matrix matrix;
    Piece piece;
    LineClearer lineClearer;
    RandomTetromino random;
    Tetromino[] sequence;

    @Before
    public void setUp() {
        sequence = new Tetromino[]{Tetromino.I, Tetromino.J, Tetromino.L};
        setUpGame(10, 22, sequence);
    }

    private void setUpGame(int matrixWidth, int matrixHeight, Tetromino[] seq) {
        sequence = seq;
        random = new RandomTetrominoStub(seq);
        matrix = new Matrix(matrixHeight, matrixWidth);
        piece = new Piece(random.nextTetromino(), matrix);
        lineClearer = new LineClearer(matrix);
        game = new Tetra(matrix, piece, random);
    }

    @Test
    public void constructorSetsMatrixCorrectly() {
        assertTrue(game.getMatrix() == matrix);
    }

    @Test
    public void constructorSetsPieceCorrectly() {
        assertTrue(game.getPiece() == piece);
    }

    @Test
    public void constructorSetsRandomCorrectly() {
        assertTrue(game.getRandom() == random);
    }

    @Test
    public void constructorSetsFirstTetrominoCorrectly() {
        assertEquals(Tetromino.I, game.getPiece().getTetromino());
    }

    @Test
    public void setComponentWorks() {
        Component component = new JPanel();
        game.setComponent(component);
        assertTrue(component == game.getComponent());
    }

    @Test
    public void linesClearedCounterStartsAtZero() {
        assertEquals(0, game.getClearedLines());
    }

    @Test
    public void timerDelayStartsAt600() {
        assertEquals(600, game.getTimerDelay());
        game.start();
        assertEquals(600, game.getTimerDelay());
    }

}
