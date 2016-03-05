package tetra.gui;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetra.logic.Direction;
import tetra.logic.Piece;
import tetra.logic.Tetra;

/**
 * Listens and reacts to keyboard events during the game. For instance, moves
 * the active piece when the user presses the arrow keys. To be more specific,
 * this class maps keyboard events to game actions, and invokes the associated
 * action whenever a keyboard event occurs. Furthermore, since actions imply a
 * possible change in game state, the general contract of this class is that it
 * also requests a GUI update after each keyboard event.
 */
public class PieceKeyListener implements KeyListener {

    private Tetra game;
    private Piece piece;
    private Component component;

    /**
     * Class constructor which specifies the game instance, and user interface
     * component to be updated.
     *
     * @param game game instance to be updated
     * @param component user interface component to be updated
     */
    public PieceKeyListener(Tetra game, Component component) {
        this.game = game;
        this.piece = game.getPiece();
        this.component = component;
    }

    /**
     * Resets the game instance to be updated.
     *
     * @param game game instance to be updated
     */
    public void setGame(Tetra game) {
        this.game = game;
        this.piece = game.getPiece();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.isGameOver()) {
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                piece.move(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                piece.move(Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                if (!game.isSoftDropActive()) {
                    game.startSoftDrop();
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_X:
                piece.rotate(true);
                break;
            case KeyEvent.VK_Z:
                piece.rotate(false);
                break;
            case KeyEvent.VK_SPACE:
                game.hardDrop();
                break;
        }

        component.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (game.isGameOver()) {
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if (game.isSoftDropActive()) {
                    game.endSoftDrop();
                }
                break;
        }
    }

}
