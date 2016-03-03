package tetra.gui;

import java.util.Random;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.RandomTetromino;
import tetra.logic.Tetra;
import tetra.logic.Tetromino;

public class CreateGame {

    private int matrixWidth;
    private int matrixHeight;

    public CreateGame(int matrixWidth, int matrixHeight) {
        this.matrixWidth = matrixWidth;
        this.matrixHeight = matrixHeight;
    }

    public Tetra create() {
        Matrix matrix = new Matrix(matrixHeight, matrixWidth);
        RandomTetromino random = new RandomTetromino(new Random());
        Tetromino first = random.nextTetromino();
        Piece piece = new Piece(first, matrix);
        return new Tetra(matrix, piece, random);
    }

}
