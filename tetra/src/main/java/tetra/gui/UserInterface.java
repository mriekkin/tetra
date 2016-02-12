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

        gamePanel = new GamePanel(game, blockSize, blockSpacing);
        container.add(gamePanel, BorderLayout.CENTER);

        frame.addKeyListener(new PieceKeyListener(piece, gamePanel));
    }

    private void setPanelSize() {
        int width = getRequiredWidth();
        int height = getRequiredHeight();
        gamePanel.setPreferredSize(new Dimension(width, height));
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

}
