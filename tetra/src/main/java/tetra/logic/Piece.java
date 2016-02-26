package tetra.logic;

/**
 * The active piece which the player can control.
 *
 * The active piece is not considered part of the matrix until it locks into
 * place.
 */
public class Piece {

    private RandomTetromino random;
    private Tetromino tetromino;
    private Direction orientation;
    private Matrix matrix;
    private int x;
    private int y;

    public Piece(RandomTetromino random, Matrix matrix) {
        this.random = random;
        this.tetromino = random.nextTetromino();
        orientation = Direction.UP;

        this.matrix = matrix;
        x = (matrix.getCols() - tetromino.width) / 2;
        y = 0;
    }

    public boolean canMove(Direction direction) {
        int newX = x + direction.dx();
        int newY = y + direction.dy();

        return !testCollision(orientation, newX, newY);
    }

    public void move(Direction direction) {
        if (!canMove(direction)) {
            return;
        }

        x += direction.dx();
        y += direction.dy();
    }

    public boolean canRotate(boolean clockwise) {
        Direction newOrientation = orientation.rotate(clockwise);
        return !testCollision(newOrientation, x, y);
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
        for (int dy = 0; dy < tetromino.height; dy++) {
            for (int dx = 0; dx < tetromino.width; dx++) {
                boolean isOccupied1 = tetromino.isOccupied(newOrientation, dx, dy);
                boolean isOccupied2 = matrix.isOccupied(newX + dx, newY + dy);

                if (isOccupied1 && matrix.isOutOfBounds(newX + dx, newY + dy)) {
                    return true;
                }

                if (isOccupied1 && isOccupied2) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean lockAndRespawn() {
        matrix.addPiece(this);
        setNextRandomTetromino();
        moveToInitialPosition();

        return !testCollision(orientation, x, y);
    }

    private void setNextRandomTetromino() {
        tetromino = random.nextTetromino();
        orientation = Direction.UP;
    }

    private void moveToInitialPosition() {
        x = (matrix.getCols() - tetromino.width) / 2;
        y = 0;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return tetromino.width;
    }

    public int getHeight() {
        return tetromino.height;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction direction) {
        this.orientation = direction;
    }

}
