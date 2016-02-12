package tetra.logic;

/**
 * Defines the 7 different tetrominoes.
 *
 * The shapes are defined on a 4x4 grid where each cell is either occupied or
 * unoccupied. Each shape has 4 orientations attained by 90 degree rotations.
 * The initial orientation is chosen such that the tetrominoes spawn
 * horizontally with their flat side pointed down.
 *
 * The definition of tetraminoes as 16 bit integers is presented in
 * http://codeincomplete.com/posts/2011/10/10/javascript_tetris/.
 *
 * @author vesarie
 */
public enum Tetromino {

    I(new int[]{0x0F00, 0x2222, 0x00F0, 0x4444}, 0x00ffff),
    J(new int[]{0x8E00, 0x6440, 0x0E20, 0x44C0}, 0x0000ff),
    L(new int[]{0x2E00, 0x4460, 0x0E80, 0xC440}, 0xffa500),
    O(new int[]{0xCC00, 0xCC00, 0xCC00, 0xCC00}, 0xffff00),
    S(new int[]{0x6C00, 0x4620, 0x06C0, 0x8C40}, 0x00ff00),
    T(new int[]{0x4E00, 0x4640, 0x0E40, 0x4C40}, 0xaa00ff),
    Z(new int[]{0xC600, 0x2640, 0x0C60, 0x4C80}, 0xff0000);

    public final int width = 4;
    public final int height = 4;

    private final int[] blocks;
    private final int color;
    private final Block blockType;

    private Tetromino(int[] blocks, int color) {
        this.blocks = blocks;
        this.color = color;
        this.blockType = new Block(color);
    }

    public boolean isOccupied(Direction orientation, int x, int y) {
        if (x < 0 || y < 0 || x >= height || y >= width) {
            return false;
        }

        int grid = blocks[orientation.ordinal()];
        int shift = y * width + x;
        int bit = 0x8000 >> shift;
        return (grid & bit) != 0;
    }

    public Block getBlock(Direction orientation, int x, int y) {
        if (!isOccupied(orientation, x, y)) {
            return null;
        }

        return blockType;
    }

    public int getColor() {
        return color;
    }

}
