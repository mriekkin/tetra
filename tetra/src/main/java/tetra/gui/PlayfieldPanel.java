package tetra.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import tetra.logic.Block;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.Tetra;

/**
 * Draws the contents of the matrix, and the piece in its current position. In
 * addition to these components, draws a background grid, which may assist with
 * piece positioning.
 */
public class PlayfieldPanel extends JPanel {

    private final int spacing;
    private final int blockSize;
    private final Color gridLineColor;
    private final Color fadeBackgroundColor;
    private final Color gameOverColor;
    private final Font gameOverFont;

    Tetra game;
    private Matrix matrix;
    private Piece piece;

    /**
     * Class constructor which specifies the game instance to be drawn, and
     * block size in pixels.
     *
     * @param game game instance to be drawn
     * @param blockSize width of one block in pixels
     * @param spaceBetweenBlocks space between adjacent blocks in pixels
     */
    public PlayfieldPanel(Tetra game, int blockSize, int spaceBetweenBlocks) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();
        this.blockSize = blockSize;
        this.spacing = spaceBetweenBlocks;

        super.setBackground(Color.BLACK);
        gridLineColor = new Color(0x1a1a1a);
        fadeBackgroundColor = new Color(0x801a1a1a, true);
        gameOverColor = new Color(0xe9f61a);
        gameOverFont = new Font("SansSerif", Font.PLAIN, 70).deriveFont(Font.BOLD);
    }

    /**
     * Resets the game instance to be drawn.
     *
     * @param game game instance to be drawn
     */
    public void setGame(Tetra game) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintGrid(g);
        paintMatrix(g);
        paintPiece(g);

        if (game.isGameOver()) {
            fadeBackground(g);
            drawGameOverString((Graphics2D) g);
        }
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
        g.fill3DRect(fillX, fillY, blockSize, blockSize, true);
    }

    private void fadeBackground(Graphics g) {
        g.setColor(fadeBackgroundColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void drawGameOverString(Graphics2D g2) {
        final double textLines = 4.25;
        int clearedLines = game.getClearedLines();
        String lines = clearedLines == 1 ? "LINE" : "LINES";

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(gameOverFont);
        g2.setColor(gameOverColor);
        drawString(g2, "GAME", 1, textLines);
        drawString(g2, "OVER", 2, textLines);
        drawString(g2, clearedLines + "", 3.25, textLines);
        drawString(g2, lines, 4.25, textLines);
    }

    private void drawString(Graphics2D g2, String text, double line, double lines) {
        FontMetrics fm = g2.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getAscent();
        int x = (getWidth() - width) / 2;
        int y = (int) ((getHeight() - height * lines) / 2.0 + (line - 1) * height);
        g2.drawString(text, x, y);
    }

}
