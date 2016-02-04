package tetra.logic;

/**
 * Defines the 7 different tetrominoes.
 *
 * The shapes are defined on a 4x4 grid where each cell is either occupied or
 * unoccupied. Each shape has 4 rotations / directions.
 *
 * The definition of tetraminoes as 16 bit integers is presented in
 * http://codeincomplete.com/posts/2011/10/10/javascript_tetris/
 *
 * @author vesarie
 */
public enum Tetromino {

    I(new int[]{0x0F00, 0x2222, 0x00F0, 0x4444}, 0),
    J(new int[]{0x44C0, 0x8E00, 0x6440, 0x0E20}, 0),
    L(new int[]{0x4460, 0x0E80, 0xC440, 0x2E00}, 0),
    O(new int[]{0xCC00, 0xCC00, 0xCC00, 0xCC00}, 0),
    S(new int[]{0x06C0, 0x8C40, 0x6C00, 0x4620}, 0),
    T(new int[]{0x0E40, 0x4C40, 0x4E00, 0x4640}, 0),
    Z(new int[]{0x0C60, 0x4C80, 0xC600, 0x2640}, 0);

    public final int ROWS = 4;
    public final int COLS = 4;
    public final int DIRECTIONS = 4;

    private final int[] blocks;
    private final int color;

    private Tetromino(int[] blocks, int color) {
        this.blocks = blocks;
        this.color = color;
    }

    public boolean isOccupied(int direction, int x, int y) {
        if (x < 0 || y < 0 || x >= ROWS || y >= COLS) {
            return false;
        }

        if (direction < 0 || direction >= DIRECTIONS) {
            return false;
        }

        int grid = blocks[direction];
        int bit = (grid >> (y * ROWS + x)) & 1;
        return bit == 1;
    }

    public int getColor() {
        return color;
    }

}
