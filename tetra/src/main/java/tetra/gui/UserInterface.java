package tetra.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
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
    private Tetra game;
    private Matrix matrix;
    private Piece piece;
    private PlayfieldPanel playfieldPanel;
    private PieceKeyListener pieceKeyListener;
    private JButton restartButton;

    private final int blockSize = 26;
    private final int blockSpacing = 2;

    public UserInterface(Tetra game) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();
        game.setGameOverListener(() -> {
            showRestartButton();
        });
    }

    @Override
    public void run() {
        frame = new JFrame("Tetra");
        frame.setTitle("Tetra");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        setPanelSize();

        frame.pack();
        hideRestartButton();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());

        playfieldPanel = new PlayfieldPanel(game, blockSize, blockSpacing);
        playfieldPanel.setDoubleBuffered(true);

        pieceKeyListener = new PieceKeyListener(game, piece, playfieldPanel);

        restartButton = new JButton("Start a new game");
        restartButton.setFocusable(false);
        restartButton.addActionListener((e) -> {
            restart();
        });

        frame.addKeyListener(pieceKeyListener);
        container.add(restartButton, BorderLayout.NORTH);
        container.add(playfieldPanel, BorderLayout.CENTER);
    }

    public void showRestartButton() {
        restartButton.setVisible(true);
        frame.pack();
    }

    public void hideRestartButton() {
        restartButton.setVisible(false);
        frame.pack();
    }

    public void restart() {
        hideRestartButton();
        game.stop();
        setGame(new CreateGame(10, 20).create());
        game.start();
    }

    private void setGame(Tetra game) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();
        this.playfieldPanel.setGame(game);
        this.pieceKeyListener.setGame(game);
        game.setComponent(playfieldPanel);
        game.setGameOverListener(() -> {
            showRestartButton();
        });
    }

    private void setPanelSize() {
        int width = getRequiredWidth();
        int height = getRequiredHeight();
        playfieldPanel.setPreferredSize(new Dimension(width, height));
    }

    private int getRequiredWidth() {
        return (blockSpacing + blockSize) * matrix.getCols() + blockSpacing;
    }

    private int getRequiredHeight() {
        return (blockSpacing + blockSize) * matrix.getRows() + blockSpacing;
    }

    public PlayfieldPanel getPlayfieldPanel() {
        return playfieldPanel;
    }

}
