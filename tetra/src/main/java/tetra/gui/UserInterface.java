package tetra.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.Tetra;

/**
 * The principal GUI class.
 *
 * Implements a GUI using the Java Swing library.
 */
public class UserInterface implements Runnable {

    private JFrame frame;
    private final Tetra game;
    private final Matrix matrix;
    private final Piece piece;
    private PlayfieldPanel playfieldPanel;

    private final int blockSize = 38;
    private final int blockSpacing = 2;

    public UserInterface(Tetra game) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();
    }

    @Override
    public void run() {
        frame = new JFrame("Tetra");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);

        createComponents(frame.getContentPane());

        setPanelSize();

        frame.pack();
        frame.setVisible(true);

        System.out.println(getRequiredWidth());
        System.out.println(getRequiredHeight());
        System.out.println("");
        System.out.println(frame.getWidth());
        System.out.println(frame.getHeight());
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());

        playfieldPanel = new PlayfieldPanel(game, blockSize, blockSpacing);
        container.add(playfieldPanel, BorderLayout.CENTER);

        frame.addKeyListener(new PieceKeyListener(game, piece, playfieldPanel));
    }

    private void setPanelSize() {
        int width = getRequiredWidth();
        int height = getRequiredHeight();
        playfieldPanel.setPreferredSize(new Dimension(width, height));
    }

    public int getRequiredWidth() {
        return (blockSpacing + blockSize) * matrix.getCols() + blockSpacing;
    }

    public int getRequiredHeight() {
        return (blockSpacing + blockSize) * matrix.getRows() + blockSpacing;
    }

    public JFrame getFrame() {
        return frame;
    }

    public PlayfieldPanel getGamePanel() {
        return playfieldPanel;
    }

}
