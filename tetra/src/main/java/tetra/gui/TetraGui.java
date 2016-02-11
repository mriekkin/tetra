package tetra.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.Tetra;

public class TetraGui implements Runnable {

    private JFrame frame;
    private Tetra game;
    private Matrix matrix;
    private Piece piece;

    public TetraGui(Tetra game) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();
    }

    @Override
    public void run() {
        frame = new JFrame("Tetra");
        frame.setPreferredSize(new Dimension(410, 810));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        GameView gameView = new GameView(game);
        container.add(gameView);

        frame.addKeyListener(new PieceKeyListener(piece, gameView));

//        int width = gameView.getRequiredWidth();
//        int height = gameView.getRequiredHeight();
//        frame.setPreferredSize(new Dimension(width, height));
    }

    public JFrame getFrame() {
        return frame;
    }

}
