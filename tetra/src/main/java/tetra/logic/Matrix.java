package tetra.logic;

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

    public void addPiece(Piece piece, int x, int y) {
        final int width = piece.getWidth();
        final int height = piece.getHeight();
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx < width; dx++) {
                Block block = piece.getBlock(x + dx, y + dy);

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
