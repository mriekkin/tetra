package tetra.logic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Contains the principal game logic loop. This class is a work-in-progress.
 */
public class Tetra implements ActionListener {

    private Matrix matrix;
    private Piece piece;
    private int clearedLines;
    private boolean gameOver;

    private Timer timer;
    private Component component;

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
        clearedLines = 0;
        this.gameOver = false;
        this.timer = new Timer(computeTimerDelay(0), this);
        this.component = null;
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

    public void startGame() {
        timer.start();
    }

    private int computeTimerDelay(int clearedLinesTotal) {
        return Math.max(100, 600 - (5 * clearedLinesTotal));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
    
    private void update() {
        updateGameLogic();

        if (component != null) {
            component.repaint();
        }
    }

    private void updateGameLogic() {
        if (gameOver) {
            return;
        }

        if (piece.canMove(Direction.DOWN)) {
            piece.move(Direction.DOWN);
        } else {
            respawn();
        }
    }

    public void hardDrop() {
        if (gameOver) {
            return;
        }

        while (piece.canMove(Direction.DOWN)) {
            piece.move(Direction.DOWN);
        }

        respawn();
    }

    public void startSoftDrop() {
        timer.setDelay(40);
        update();
    }

    public void endSoftDrop() {
        timer.setDelay(computeTimerDelay(clearedLines));
        //update();
    }

    private void respawn() {
        int yMin = piece.getY();
        int yMax = piece.getY() + piece.getHeight() - 1;

        boolean canContinue = piece.lockAndRespawn();

        if (!canContinue) {
            gameOver();
            return;
        }

        clearCompleteLines(yMin, yMax);
    }

    private void clearCompleteLines(int yMin, int yMax) {
        int n = matrix.clearCompleteLines(yMin, yMax);

        if (n > 0) {
            clearedLines += n;
            timer.setDelay(computeTimerDelay(clearedLines));
            //System.out.println(timer.getDelay());
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void gameOver() {
        gameOver = true;
        System.out.println("Game over!");
    }

}
