package tetra.logic;

/**
 * A geometric shape composed of 4 blocks. The definition of a tetromino
 * specifies that the blocks must connected orthogonally, allowing for 7
 * distinct shapes. This class defines these shapes on a 4x4 grid where each
 * cell is either occupied or unoccupied.
 * <p>
 * It is the responsibility of this class to describe how these shapes rotate.
 * According to the rules of the game, each shape has 4 available orientations
 * attained by 90 degree rotations. The initial orientation is always up, chosen
 * such that the tetromino spawns horizontally with its flat side pointed down.
 * <p>
 * From a conceptual point of view, it would be natural to implement this class
 * as an array of blocks. The implementation of this class, however, takes a
 * more economic approach. The shapes are defined as 16 bit integers, described
 * in more detail in
 * <a href="http://codeincomplete.com/posts/2011/10/10/javascript_tetris/">this
 * source</a>. It turns out, that this representation is not only compact but
 * also quite convenient if one knows how to read hexadecimals.
 */
public enum Tetromino {

    I(new int[]{0x0F00, 0x2222, 0x00F0, 0x4444}, 0x00ffff),
    J(new int[]{0x8E00, 0x6440, 0x0E20, 0x44C0}, 0x0000ff),
    L(new int[]{0x2E00, 0x4460, 0x0E80, 0xC440}, 0xffa500),
    O(new int[]{0x6600, 0x6600, 0x6600, 0x6600}, 0xffff00),
    S(new int[]{0x6C00, 0x4620, 0x06C0, 0x8C40}, 0x00ff00),
    T(new int[]{0x4E00, 0x4640, 0x0E40, 0x4C40}, 0xaa00ff),
    Z(new int[]{0xC600, 0x2640, 0x0C60, 0x4C80}, 0xff0000);

    /**
     * The width of a grid, which is constant.
     */
    public final int width = 4;

    /**
     * The height of a grid, which is contant.
     */
    public final int height = 4;

    private final int[] orientations;
    private final int color;
    private final Block blockType;

    /**
     * Class constructor which specifies an array of shapes, one for each
     * orientation, and color. The color is specified as an RGB color code.
     *
     * @param orientations an array of shapes, one for each orientation
     * @param color the color for this tetromino, specified as an RGB color code
     */
    private Tetromino(int[] orientations, int color) {
        this.orientations = orientations;
        this.color = color;
        this.blockType = new Block(color);
    }

    /**
     * Returns true if there's a block in the specified (x,&nbsp;y) position;
     * false otherwise. The result depends on the specified orientation, since
     * the configuration of blocks is different for each orientation. If the x-
     * or y-coordinate is out of range, returns false.
     *
     * @param orientation the orientation to inspect. The configuration of
     * blocks is different for each orientation.
     * @param x x-coordinate of the position to inspect
     * @param y y-coordinate of the position to inspect
     * @return true if there's a block in the specified position, false
     * otherwise
     */
    public boolean isOccupied(Direction orientation, int x, int y) {
        if (x < 0 || y < 0 || x >= height || y >= width) {
            return false;
        }

        int grid = orientations[orientation.ordinal()];
        int shift = y * width + x;
        int bit = 0x8000 >> shift;
        return (grid & bit) != 0;
    }

    /**
     * Returns the block in the specified (x,&nbsp;y) position, or null if the
     * position is empty. The result depends on the specified orientation, since
     * the configuration of blocks is different for each orientation. If the x-
     * or y-coordinate is out of range, returns null.
     *
     * @param orientation the orientation to inspect. The configuration of
     * blocks is different for each orientation.
     * @param x x-coordinate of the element to return
     * @param y y-coordinate of the element to return
     * @return the block at the specified position, or null if the position is
     * empty
     */
    public Block getBlock(Direction orientation, int x, int y) {
        if (!isOccupied(orientation, x, y)) {
            return null;
        }

        return blockType;
    }

    /**
     * Returns the color of this tetromino, specified as an RGB color code.
     *
     * @return the color of this tetromino, specified as an RGB color code
     */
    public int getColor() {
        return color;
    }

}
