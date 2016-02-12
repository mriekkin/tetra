package tetra.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetra.logic.Block;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.Tetra;

/**
 * Draws the game view
 */
public class GamePanel extends JPanel {

    private final int spacing;
    private final int blockSize;
    private final Color gridLineColor;

    Tetra game;
    private Matrix matrix;
    private Piece piece;

    public GamePanel(Tetra game, int blockSize, int spaceBetweenBlocks) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();

        super.setBackground(Color.BLACK);

        gridLineColor = new Color(0x1a1a1a);

        this.blockSize = blockSize;
        this.spacing = spaceBetweenBlocks;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintGrid(g);
        paintMatrix(g);
        paintPiece(g);
    }

    private void paintGrid(Graphics g) {
        g.setColor(gridLineColor);

        final int cellSize = spacing + blockSize;
        final int width = cellSize * matrix.getCols() + spacing;
        final int height = cellSize * matrix.getRows() + spacing;

        for (int y = 0; y < matrix.getRows(); y++) {
            g.drawLine(0, y * cellSize, width, y * cellSize);
        }

        for (int x = 0; x < matrix.getCols(); x++) {
            g.drawLine(x * cellSize, 0, x * cellSize, height);
        }
    }

    private void paintMatrix(Graphics g) {
        for (int y = 0; y < matrix.getRows(); y++) {
            for (int x = 0; x < matrix.getCols(); x++) {
                Block block = matrix.getBlock(x, y);

                if (block != null) {
                    paintBlock(g, x, y, block);
                }
            }
        }
    }

    private void paintPiece(Graphics g) {
        for (int dy = 0; dy < piece.getHeight(); dy++) {
            for (int dx = 0; dx < piece.getWidth(); dx++) {
                int x = piece.getX() + dx;
                int y = piece.getY() + dy;
                Block block = piece.getBlock(x, y);

                if (block != null) {
                    paintBlock(g, x, y, block);
                }
            }
        }
    }

    private void paintBlock(Graphics g, int x, int y, Block block) {
        int fillX = x * (spacing + blockSize) + spacing;
        int fillY = y * (spacing + blockSize) + spacing;
        g.setColor(block.getColorAwt());
        g.fillRect(fillX, fillY, blockSize, blockSize);
    }

}
