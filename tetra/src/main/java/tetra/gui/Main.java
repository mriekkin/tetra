package tetra.gui;

import java.util.Random;
import javax.swing.SwingUtilities;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.RandomTetromino;
import tetra.logic.Tetra;
import tetra.logic.Tetromino;

/**
 * Program entry point.
 */
public class Main {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(20, 10);
        RandomTetromino random = new RandomTetromino(new Random());
        Tetromino first = random.nextTetromino();
        Piece piece = new Piece(first, matrix);
        Tetra game = new Tetra(matrix, piece, random);
        UserInterface gui = new UserInterface(game);

        SwingUtilities.invokeLater(gui);

        while (gui.getGamePanel() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("GamePanel hasn't been created yet.");
            }
        }

        game.setComponent(gui.getGamePanel());
        
        game.start();
    }

}
