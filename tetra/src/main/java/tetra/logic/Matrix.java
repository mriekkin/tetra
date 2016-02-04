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
