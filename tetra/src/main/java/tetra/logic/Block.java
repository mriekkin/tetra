package tetra.logic;

import java.awt.Color;

/**
 * A block of specified color in a matrix. Blocks are immutable and contain no
 * other information beyond color.
 * <p>
 * One block can be used in several (x,&nbsp;y) positions, since blocks don't
 * contain coordinates. This implies that one block can be used to represent all
 * blocks of the same color. This might be beneficial in the interest of memory
 * management since well over 1&nbsp;000 blocks may be created and destroyed
 * over the course of one game. We prefer to avoid creating a large number of
 * objects with a short life-span. Since there are 7 tetromino types, we need
 * only 7 block instances.
 */
public class Block {

    private final int color;
    private final Color colorAwt;

    /**
     * Class constructor which specifies the RGB color code for this block.
     *
     * @param color the combined RGB components
     */
    public Block(int color) {
        this.color = color;
        this.colorAwt = new Color(color);
    }

    /**
     * Returns the RGB color code for this block.
     *
     * @return the combined RGB components
     */
    public int getColor() {
        return color;
    }

    /**
     * Returns a Color object constructed with the RGB color code.
     *
     * @return Color object
     * @see java.awt.Color
     */
    public Color getColorAwt() {
        return colorAwt;
    }

}
