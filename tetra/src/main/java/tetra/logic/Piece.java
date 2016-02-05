package tetra.logic;

public class Piece {

    private Tetromino tetromino;
    private Matrix matrix;
    private int x;
    private int y;
    private Direction orientation;

    public Piece(Tetromino tetromino, Matrix matrix) {
        this.tetromino = tetromino;
        this.matrix = matrix;
        x = (matrix.getCols() - tetromino.COLS) / 2;
        y = 0;
        orientation = Direction.UP;
    }

    public boolean canMoveTo(int newX, int newY) {
        return !testCollision(orientation, newX, newY);
    }

    public void moveTo(int newX, int newY) {
        if (!canMoveTo(newX, newY)) {
            return;
        }

        x = newX;
        y = newY;
    }

    public boolean canMove(Direction direction) {
        int newX = x + direction.dx();
        int newY = y + direction.dy();
        return testCollision(orientation, newX, newY);
    }

    public void move(Direction direction) {
        if (!canMove(direction)) {
            return;
        }

        x += direction.dx();
        y += direction.dy();
    }

    public boolean canRotate(boolean clockwise) {
        Direction newDirection = orientation.rotate(clockwise);
        return testCollision(newDirection, x, y);
    }

    public void rotate(boolean clockwise) {
        if (!canRotate(clockwise)) {
            return;
        }

        orientation = orientation.rotate(clockwise);
    }

    private boolean testCollision(Direction newOrientation, int newX, int newY) {
        for (int row = 0; row < tetromino.ROWS; row++) {
            for (int col = 0; col < tetromino.COLS; col++) {
                if (tetromino.isOccupied(newOrientation, col, row)
                        && matrix.isOccupied(newX + col, newY + row)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction direction) {
        this.orientation = direction;
    }

}
