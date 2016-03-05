package tetra.logic;

/**
 * A rectangular matrix consisting of empty cells and blocks. This is the
 * playing field of Tetris. A typical matrix size is 10x20 blocks for the
 * visible playing field. In addition to this, many Tetris variants include 2 or
 * more hidden rows above the visible matrix.
 * <p>
 * It should be noted that a matrix consists of blocks, not tetrominoes. The
 * distinction being, that when a tetromino locks in place, it disassembles from
 * one entity into 4. In other words, each block is independent from any other,
 * regardless of whether they originate from the same piece.
 */
public class Matrix {

    private Block[][] grid;
    private final int rows;
    private final int cols;
    private LineClearer lineClearer;

    /**
     * Class constructor which specifies the size of this matrix.
     *
     * @param rows the number of rows, or height, for this matrix
     * @param cols the number of columns, or width, for this matrix
     */
    public Matrix(int rows, int cols) {
        this.grid = new Block[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.lineClearer = new LineClearer(this);
    }

    /**
     * Returns the number of rows in this matrix, or height.
     *
     * @return the number of rows in this matrix, or height
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in this matrix, or width.
     *
     * @return the number of columns in this matrix, or width
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the block at the specified (x,&nbsp;y) position in this matrix,
     * or null if the position is empty. Returns null also if the x- or
     * y-coordinate is out of range.
     *
     * @param x x-coordinate of the element to return
     * @param y y-coordinate of the element to return
     * @return the block at the specified position, or null if the position is
     * empty
     */
    public Block getBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= cols || y >= rows) {
            return null;
        }

        return grid[y][x];
    }

    /**
     * Replaces the element at the specified position in this matrix with the
     * specified block. Does nothing if the x- or y-coordinate is out of range.
     *
     * @param x x-coordinate of the element to replace
     * @param y y-coordinate of the element to replace
     * @param block block to be stored at the specified position
     */
    public void setBlock(int x, int y, Block block) {
        if (x < 0 || y < 0 || x >= cols || y >= rows) {
            return;
        }

        grid[y][x] = block;
    }

    /**
     * Returns true if there's a block in the specified (x,&nbsp;y) position;
     * false otherwise. Returns false also if the x- or y-coordinate is out of
     * range.
     *
     * @param x x-coordinate of the position to inspect
     * @param y y-coordinate of the position to inspect
     * @return true if there's a block in the specified position, false
     * otherwise
     */
    public boolean isOccupied(int x, int y) {
        return getBlock(x, y) != null;
    }

    /**
     * Returns true if the specified (x,&nbsp;y) position is outside the
     * boundaries of this matrix; false otherwise.
     *
     * @param x x-coordinate of the position
     * @param y y-coordinate of the position
     * @return true if the specified position is outside the boundaries of this
     * matrix, false otherwise
     */
    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= cols || y < 0 || y >= rows;
    }

    /**
     * Disassembles the piece into independent blocks, and adds them to this
     * matrix. The blocks are added to the current position of the piece. After
     * this the piece will intersect with the matrix and should be respawned. It
     * should be noted that this method does not modify the piece in any way.
     *
     * @param piece the piece whose blocks to add to this matrix
     */
    public void addPiece(Piece piece) {
        for (int dy = 0; dy < piece.getHeight(); dy++) {
            for (int dx = 0; dx < piece.getWidth(); dx++) {
                int x = piece.getX() + dx;
                int y = piece.getY() + dy;
                Block block = piece.getBlock(x, y);

                if (block != null) {
                    setBlock(x, y, block);
                }
            }
        }
    }

    /**
     * Clears complete lines within the range yMin..yMax, including the
     * specified lines yMin and yMax. If there are complete lines to be cleared,
     * rows above the cleared lines will be shifted down accordingly.
     *
     * @param yMin the first row to inspect, inclusive
     * @param yMax the last row to investigate, inclusive
     * @return the number of cleared lines
     */
    public int clearCompleteLines(int yMin, int yMax) {
        return lineClearer.clearCompleteLinesAndShiftDown(yMin, yMax);
    }

}
