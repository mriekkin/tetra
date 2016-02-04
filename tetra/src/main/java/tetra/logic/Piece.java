package tetra.logic;

public class Piece {

    private Tetromino tetromino;
    private Matrix matrix;

    public Piece(Tetromino tetromino, Matrix matrix) {
        this.tetromino = tetromino;
        this.matrix = matrix;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }
    
    

}
