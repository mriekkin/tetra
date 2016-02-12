package tetra.logic;

/**
 * Represents a rectangular matrix consisting of empty cells and Blocks.
 */
public class Matrix {

    private Block[][] grid;
    private final int rows;
    private final int cols;

    public Matrix(int rows, int cols) {
        this.grid = new Block[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public Block getBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= cols || y >= rows) {
            return null;
        }

        return grid[y][x];
    }

    public void setBlock(int x, int y, Block block) {
        if (x < 0 || y < 0 || x >= cols || y >= rows) {
            return;
        }

        grid[y][x] = block;
    }

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

    public boolean isOccupied(int x, int y) {
        return getBlock(x, y) != null;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

}
