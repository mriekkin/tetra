package tetra.logic;

/**
 * Contains the principal game logic loop. This class is a work-in-progress.
 */
public class Tetra {

    private Matrix matrix;
    private Piece piece;

    /**
     * Class constructor which specifies the matrix and piece to use for this
     * game.
     *
     * @param matrix matrix for this game
     * @param piece piece for this game
     */
    public Tetra(Matrix matrix, Piece piece) {
        this.matrix = matrix;
        this.piece = piece;
    }

    /**
     * Returns the matrix used for this game.
     *
     * @return the matrix used for this game
     */
    public Matrix getMatrix() {
        return matrix;
    }

    /**
     * Returns the piece used for this game.
     *
     * @return the piece used for this game
     */
    public Piece getPiece() {
        return piece;
    }

}
