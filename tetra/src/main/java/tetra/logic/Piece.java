package tetra.logic;

public class Piece {

    private Matrix matrix;
    private int x;
    private int y;
    private Tetromino tetromino;
    private Direction orientation;

    public Piece(Tetromino tetromino, Matrix matrix) {
        this.tetromino = tetromino;
        orientation = Direction.UP;

        this.matrix = matrix;
        x = (matrix.getCols() - tetromino.WIDTH) / 2;
        y = 0;
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

    public Block getBlock(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;
        return tetromino.getBlock(orientation, dx, dy);
    }

    private boolean testCollision(Direction newOrientation, int newX, int newY) {
        for (int dy = 0; dy < tetromino.HEIGHT; dy++) {
            for (int dx = 0; dx < tetromino.WIDTH; dx++) {
                if (tetromino.isOccupied(newOrientation, dx, dy)
                        && matrix.isOccupied(newX + dx, newY + dy)) {
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

    public int getWidth() {
        return tetromino.WIDTH;
    }

    public int getHeight() {
        return tetromino.HEIGHT;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction direction) {
        this.orientation = direction;
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

}
