package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetraUpdateGameTest {

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
    public void updateMovesPieceDownOneStep() {
        assertEquals(0, piece.getY());
        game.update();
        assertEquals(1, piece.getY());
        game.update();
        assertEquals(2, piece.getY());
        game.update();
        assertEquals(3, piece.getY());
    }

    @Test
    public void actionPerformedCallsUpdate() {
        assertEquals(0, piece.getY());
        game.actionPerformed(null);
        assertEquals(1, piece.getY());
    }

    @Test
    public void updateRespawnsWhenPieceReachesLowerLimit() {
        sequence = new Tetromino[]{Tetromino.T, Tetromino.Z};
        setUpGame(10, 22, sequence);
        while (piece.canMove(Direction.DOWN)) {
            piece.move(Direction.DOWN);
        }
        assertEquals(20, piece.getY());
        assertEquals(Tetromino.T, piece.getTetromino());
        game.update();
        assertEquals(0, piece.getY());
        assertEquals(Tetromino.Z, piece.getTetromino());
    }

    @Test
    public void respawnSetsNextTetrominoCorrectly() {
        assertEquals(Tetromino.I, piece.getTetromino());
        game.hardDrop();
        assertEquals(Tetromino.J, piece.getTetromino());
        game.hardDrop();
        assertEquals(Tetromino.L, piece.getTetromino());
    }

    @Test
    public void respawnLocksPieceIntoMatrix() {
        game.hardDrop();
        assertEquals(true, matrix.isOccupied(3, 21));
        assertEquals(true, matrix.isOccupied(4, 21));
        assertEquals(true, matrix.isOccupied(5, 21));
        assertEquals(true, matrix.isOccupied(6, 21));
    }

    @Test
    public void respawnSetsGameOverIfNextPieceIntersectsWithMatrix() {
        sequence = new Tetromino[]{Tetromino.O};
        setUpGame(10, 10, sequence);
        game.hardDrop();
        game.hardDrop();
        game.hardDrop();
        game.hardDrop();
        assertEquals(false, game.isGameOver());
        game.hardDrop();
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void doThingsAfterGameOver() {
        sequence = new Tetromino[]{Tetromino.O};
        setUpGame(10, 10, sequence);
        game.hardDrop();
        game.hardDrop();
        game.hardDrop();
        game.hardDrop();
        game.hardDrop();
        assertEquals(true, game.isGameOver());
        game.update();
        game.hardDrop();
        assertEquals(true, game.isGameOver());
    }

}
