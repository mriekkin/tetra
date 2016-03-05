package tetra.logic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Implements game logic, which updates game state according to the rules of the
 * game. The game is updated as a function of time, the current state of the
 * game, and user actions. This class handles most of the work by delegating it
 * to supporting classes.
 */
public class Tetra implements ActionListener {

    private Matrix matrix;
    private Piece piece;
    private RandomTetromino random;
    private int clearedLines;
    private boolean gameOver;
    private boolean isSoftDropActive;

    private Timer timer;
    private Component component;
    private UpdateListener gameOverListener;

    /**
     * Class constructor which specifies the matrix and piece for this game.
     *
     * @param matrix matrix for this game
     * @param piece piece for this game
     * @param random random generator for this game
     */
    public Tetra(Matrix matrix, Piece piece, RandomTetromino random) {
        this.matrix = matrix;
        this.piece = piece;
        this.random = random;
        clearedLines = 0;
        this.gameOver = false;
        this.isSoftDropActive = false;
        this.timer = new Timer(computeTimerDelay(0), this);
        this.component = null;
        this.gameOverListener = null;
    }

    /**
     * Returns the matrix of this game.
     *
     * @return the matrix of this game
     */
    public Matrix getMatrix() {
        return matrix;
    }

    /**
     * Returns the piece of this game.
     *
     * @return the piece of this game
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Returns the random generator of this game.
     *
     * @return the random generator of this game
     */
    public RandomTetromino getRandom() {
        return random;
    }

    /**
     * Returns the total number of cleared lines in this game.
     *
     * @return total number of cleared lines in this game
     */
    public int getClearedLines() {
        return clearedLines;
    }

    /**
     * Returns the user interface component registered to be notified of changes
     * to game state.
     *
     * @return registered user interface component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Registers the specified user interface component to be notified of
     * changes to game state.
     *
     * @param component user interface component to register
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * Starts the game, after which game state will update at regular intervals.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stops the game. After this this game instance should be disposed.
     */
    public void stop() {
        timer.stop();
        timer.removeActionListener(this);
    }

    /**
     * Returns the current delay between periodic game state updates, which
     * dictates the speed of descent.
     *
     * @return current delay, which dictates the speed of descent
     */
    public int getTimerDelay() {
        return timer.getDelay();
    }

    /**
     * Returns the correct timer delay as a function of cleared lines.
     *
     * @param clearedLinesTotal total number of cleared lines
     * @return timer delay, correct for the specified number of lines
     */
    private int computeTimerDelay(int clearedLinesTotal) {
        return Math.max(100, 600 - (5 * clearedLinesTotal));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    /**
     * Updates game logic, and notifies the user interface that it should update
     * itself.
     */
    public void update() {
        updateGameLogic();

        if (component != null) {
            component.repaint();
        }
    }

    /**
     * Tries to move the piece down one step if possible, or respawns the piece
     * if it cannot move further down.
     */
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

    /**
     * Drops the piece as far down as possible, then respawns the piece. The
     * movement of the piece is instantaneous.
     */
    public void hardDrop() {
        if (gameOver) {
            return;
        }

        while (piece.canMove(Direction.DOWN)) {
            piece.move(Direction.DOWN);
        }

        respawn();
    }

    /**
     * Increases the speed of descent, until told otherwise.
     */
    public void startSoftDrop() {
        if (isSoftDropActive) {
            return;
        }

        this.isSoftDropActive = true;
        timer.setInitialDelay(0);
        timer.setDelay(50);
        timer.restart();
    }

    /**
     * Resumes the ordinary speed of descent.
     */
    public void endSoftDrop() {
        if (!isSoftDropActive) {
            return;
        }

        this.isSoftDropActive = false;
        int delay = computeTimerDelay(clearedLines);
        timer.setInitialDelay(delay);
        timer.setDelay(delay);
        timer.restart();
    }

    /**
     * Returns true if the piece is in a mode of accelerated descent.
     *
     * @return true if currectly active, false otherwise
     */
    public boolean isSoftDropActive() {
        return isSoftDropActive;
    }

    private void respawn() {
        int yMin = piece.getY();
        int yMax = piece.getY() + piece.getHeight() - 1;

        piece.lock();
        Tetromino next = random.nextTetromino();
        boolean canContinue = piece.respawn(next);

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
            if (!isSoftDropActive) {
                timer.setDelay(computeTimerDelay(clearedLines));
            }
        }
    }

    /**
     * Returns true if the game has ended.
     *
     * @return true if the game has ended, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    private void gameOver() {
        gameOver = true;
        timer.setDelay(computeTimerDelay(0));
        isSoftDropActive = false;

        if (gameOverListener != null) {
            gameOverListener.update();
        }
    }

    /**
     * Returns a listener that has registered to be notified when the game ends.
     *
     * @return registered listener
     */
    public UpdateListener getGameOverListener() {
        return gameOverListener;
    }

    /**
     * Registers a listener to be notified when the game ends. The listener will
     * receive at most one notification.
     *
     * @param gameOverListener listener to be registered
     */
    public void setGameOverListener(UpdateListener gameOverListener) {
        this.gameOverListener = gameOverListener;
    }

}
