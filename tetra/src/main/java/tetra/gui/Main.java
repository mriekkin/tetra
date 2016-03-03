package tetra.gui;

import javax.swing.SwingUtilities;
import tetra.logic.Tetra;

/**
 * Program entry point.
 */
public class Main {

    public static void main(String[] args) {
        Tetra game = new CreateGame(10, 20).create();
        UserInterface gui = new UserInterface(game);

        SwingUtilities.invokeLater(gui);

        while (gui.getPlayfieldPanel() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("PlayfieldPanel hasn't been created yet.");
            }
        }

        game.setComponent(gui.getPlayfieldPanel());
        game.start();
    }

}
