package tetra.gui;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetra.logic.Direction;
import tetra.logic.Piece;
import tetra.logic.Tetra;

/**
 * Listens and reacts to keyboard events during the game.
 *
 * For instance, moves the active piece when the user presses the arrow keys.
 */
public class PieceKeyListener implements KeyListener {

    private Tetra game;
    private Piece piece;
    private Component component;

    public PieceKeyListener(Tetra game, Piece piece, Component component) {
        this.game = game;
        this.piece = piece;
        this.component = component;
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
                //piece.move(Direction.DOWN);
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
    }

}
