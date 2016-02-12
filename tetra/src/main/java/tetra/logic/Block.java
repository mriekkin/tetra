package tetra.logic;

import java.awt.Color;

/**
 * Represents a block of specified color in the matrix.
 *
 * Since Blocks are immutable and don't contain coordinates, one can use the
 * same block object in several (x, y) positions. In this manner one only really
 * needs one Block per each type of Tetromino.
 */
public class Block {

    private final int color;
    private final Color colorAwt;

    public Block(int color) {
        this.color = color;
        this.colorAwt = new Color(color);
    }

    public int getColor() {
        return color;
    }

    public Color getColorAwt() {
        return colorAwt;
    }

}
