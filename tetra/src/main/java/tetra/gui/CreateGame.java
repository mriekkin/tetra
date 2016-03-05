package tetra.gui;

import java.util.Random;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.RandomTetromino;
import tetra.logic.Tetra;
import tetra.logic.Tetromino;

/**
 * Encapsulates the action of creating a new game.
 */
public class CreateGame {

    private int matrixWidth;
    private int matrixHeight;

    /**
     * Class constructor which specifies the size of the playfield matrix.
     *
     * @param matrixWidth width of the playfield matrix (the number of columns)
     * @param matrixHeight height of the playfield matrix (the number of rows)
     */
    public CreateGame(int matrixWidth, int matrixHeight) {
        this.matrixWidth = matrixWidth;
        this.matrixHeight = matrixHeight;
    }

    /**
     * Creates a set of objects which describe a new game. Returns a reference
     * to the created game instance. The other objects linked to Tetra can be
     * obtained by querying the appropriate get-methods.
     *
     * @return a new game instance
     */
    public Tetra create() {
        Matrix matrix = new Matrix(matrixHeight, matrixWidth);
        RandomTetromino random = new RandomTetromino(new Random());
        Tetromino first = random.nextTetromino();
        Piece piece = new Piece(first, matrix);
        return new Tetra(matrix, piece, random);
    }

}
