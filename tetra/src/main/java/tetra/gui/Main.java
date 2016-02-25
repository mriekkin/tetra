package tetra.gui;

import java.util.Random;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import tetra.logic.LineClearer;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.RandomTetromino;
import tetra.logic.Tetra;

/**
 * Program entry point: contains the main()
 */
public class Main {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(20, 10);
        RandomTetromino random = new RandomTetromino(new Random());
        Piece piece = new Piece(random, matrix);
        LineClearer lineClearer = new LineClearer(matrix);
        Tetra game = new Tetra(matrix, piece, lineClearer);
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

        Timer timer = new Timer(250, game);
        timer.start();
    }

}
