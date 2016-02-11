package tetra.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;
import tetra.logic.Block;
import tetra.logic.Matrix;
import tetra.logic.Piece;
import tetra.logic.Tetra;

public class GameView extends JPanel {

    private final int border = 2;
    private final int interior = 38;

    Tetra game;
    private Matrix matrix;
    private Piece piece;

    private HashMap<Integer, Color> colors;

    public GameView(Tetra game) {
        this.game = game;
        this.matrix = game.getMatrix();
        this.piece = game.getPiece();

        super.setBackground(Color.BLACK);
    }

    public int getRequiredWidth() {
        return (border + interior) * 10 + border;
    }

    public int getRequiredHeight() {
        return (border + interior) * 20 + border;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintMatrix(g);
        paintPiece(g);
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
        int fillX = x * (border + interior) + border;
        int fillY = y * (border + interior) + border;
        g.setColor(new Color(block.getColor()));
        g.fillRect(fillX, fillY, interior, interior);
    }

}
