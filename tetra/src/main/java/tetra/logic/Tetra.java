package tetra.logic;

/**
 * Contains the principal game logic loop.
 */
public class Tetra {

    private Matrix matrix;
    private Piece piece;

    public Tetra(Matrix matrix, Piece piece) {
        this.matrix = matrix;
        this.piece = piece;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public Piece getPiece() {
        return piece;
    }

}
