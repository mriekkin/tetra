package tetra.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.Tetra;

public class UserInterface implements Runnable {

    private JFrame frame;
    private final Tetra game;
    private final Matrix matrix;
    private final Piece piece;
    private GamePanel gamePanel;

    private final int BLOCK_SIZE = 38;
    private final int BLOCK_SPACING = 2;

    public UserInterface(Tetra game) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();
    }

    @Override
    public void run() {
        frame = new JFrame("Tetra");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        gamePanel = new GamePanel(game, BLOCK_SIZE, BLOCK_SPACING);
        container.add(gamePanel, BorderLayout.CENTER);

        frame.addKeyListener(new PieceKeyListener(piece, gamePanel));
    }

    private void setPanelSize() {
        int width = getRequiredWidth();
        int height = getRequiredHeight();
        gamePanel.setPreferredSize(new Dimension(width, height));
    }

    public int getRequiredWidth() {
        return (BLOCK_SPACING + BLOCK_SIZE) * matrix.getCols() + BLOCK_SPACING;
    }

    public int getRequiredHeight() {
        return (BLOCK_SPACING + BLOCK_SIZE) * matrix.getRows() + BLOCK_SPACING;
    }

    public JFrame getFrame() {
        return frame;
    }

}
