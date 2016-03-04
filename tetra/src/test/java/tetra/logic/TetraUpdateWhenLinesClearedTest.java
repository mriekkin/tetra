package tetra.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TetraUpdateGameAfterLineClearsTest {

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

    private void setUpGameForClearingOneLine() {
        sequence = new Tetromino[]{Tetromino.T};
        setUpGame(10, 22, sequence);
        MatrixHelper.fillRow(matrix, 21, new Block(1010));
        matrix.setBlock(4, 21, null);
        piece.rotate(true);
    }

    private void setUpGameForClearing5Lines() {
        sequence = new Tetromino[]{Tetromino.I};
        setUpGame(10, 22, sequence);
        piece.rotate(true);
        MatrixHelper.fillRow(matrix, 17, new Block(1010));
        MatrixHelper.fillRow(matrix, 18, new Block(1010));
        MatrixHelper.fillRow(matrix, 19, new Block(1010));
        MatrixHelper.fillRow(matrix, 20, new Block(1010));
        MatrixHelper.fillRow(matrix, 21, new Block(1010));
        matrix.setBlock(5, 17, null);
        matrix.setBlock(5, 18, null);
        matrix.setBlock(5, 19, null);
        matrix.setBlock(5, 20, null);
        matrix.setBlock(5, 21, null);
    }

    @Test
    public void clearedLinesCounterIncreasesWhenOneLineIsCleared() {
        setUpGameForClearingOneLine();
        assertEquals(0, game.getClearedLines());
        game.hardDrop();
        assertEquals(1, game.getClearedLines());
    }

    @Test
    public void clearedLinesCounterIncreasesWhen4LinesAreCleared() {
        setUpGameForClearing5Lines();
        game.hardDrop();
        assertEquals(4, game.getClearedLines());
    }

    @Test
    public void clearedLinesCounterIncreasesWhen5LinesAreCleared() {
        setUpGameForClearing5Lines();
        game.hardDrop();
        piece.rotate(true);
        game.hardDrop();
        assertEquals(5, game.getClearedLines());
    }

    @Test
    public void timerDelayIncreases5msWhenOneLineIsCleared() {
        setUpGameForClearingOneLine();
        assertEquals(600, game.getTimerDelay());
        game.hardDrop();
        assertEquals(600 - 5, game.getTimerDelay());
    }

    @Test
    public void timerDelayIncreases5msForEachClearedLine() {
        setUpGameForClearing5Lines();
        game.hardDrop();
        assertEquals(600 - 4 * 5, game.getTimerDelay());
        piece.rotate(true);
        game.hardDrop();
        assertEquals(600 - 5 * 5, game.getTimerDelay());
    }

}
