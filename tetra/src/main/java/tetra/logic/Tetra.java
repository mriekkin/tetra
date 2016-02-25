package tetra.logic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contains the principal game logic loop. This class is a work-in-progress.
 */
public class Tetra implements ActionListener {

    private Matrix matrix;
    private Piece piece;
    private LineClearer lineClearer;
    private Component component;
    private boolean gameOver;

    /**
     * Class constructor which specifies the matrix and piece to use for this
     * game.
     *
     * @param matrix matrix for this game
     * @param piece piece for this game
     * @param lineClearer line clear analyzer for this game
     */
    public Tetra(Matrix matrix, Piece piece, LineClearer lineClearer) {
        this.matrix = matrix;
        this.piece = piece;
        this.lineClearer = lineClearer;
        this.component = null;
        this.gameOver = false;
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

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            updateGame();
        }

        if (component != null) {
            component.repaint();
        }
    }

    private void updateGame() {
        if (piece.canMove(Direction.DOWN)) {
            piece.move(Direction.DOWN);
        } else {
            respawn();
        }
    }

    private void respawn() {
        int yMin = piece.getY();
        int yMax = piece.getY() + piece.getHeight() - 1;

        boolean canContinue = piece.lockAndRespawn();

        if (!canContinue) {
            gameOver();
            return;
        }

        lineClearer.clearCompleteLinesAndShift(yMin, yMax);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void gameOver() {
        gameOver = true;
        System.out.println("Game over!");
    }

}
