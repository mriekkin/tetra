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

    @Before
    public void setUp() {
        int[] integers = new int[]{0, 1, 2, 3, 4, 5, 6};
        random = new RandomTetromino(new RandomIntStub(integers));
        matrix = new Matrix(22, 10);
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

}
