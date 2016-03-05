package tetra.logic;

/**
 * The active piece which the player can control. It should be noted, that the
 * active piece is not considered part of the matrix until it locks into place.
 */
public class Piece {

    private Tetromino tetromino;
    private Direction orientation;
    private Matrix matrix;
    private int x;
    private int y;

    /**
     * Class constructor which specifies the first tetromino, and the matrix
     * within which this piece will travel.
     *
     * @param tetromino the first tetromino
     * @param matrix the matrix of this game
     */
    public Piece(Tetromino tetromino, Matrix matrix) {
        this.tetromino = tetromino;
        orientation = Direction.UP;

        this.matrix = matrix;
        x = (matrix.getCols() - tetromino.width) / 2;
        y = 0;
    }

    /**
     * Returns true if it is possible to take one step in the specified
     * direction.
     *
     * @param direction direction to step in
     * @return true if the specified translation is possible, false otherwise
     */
    public boolean canMove(Direction direction) {
        int newX = x + direction.dx();
        int newY = y + direction.dy();

        return !testCollision(orientation, newX, newY);
    }

    /**
     * Moves one step in the specified direction, if possible.
     *
     * @param direction direction to step in
     */
    public void move(Direction direction) {
        if (!canMove(direction)) {
            return;
        }

        x += direction.dx();
        y += direction.dy();
    }

    /**
     * Returns true if it is possible to rotate 90&deg; in the specified
     * direction.
     *
     * @param clockwise true indicates clockwise, false indicates
     * counterclockwise
     * @return true if the specified rotation is possible, false otherwise
     */
    public boolean canRotate(boolean clockwise) {
        Direction newOrientation = orientation.rotate(clockwise);
        return !testCollision(newOrientation, x, y);
    }

    /**
     * Rotates 90&deg; in the specified direction, if possible.
     *
     * @param clockwise true indicates clockwise, false indicates
     * counterclockwise
     */
    public void rotate(boolean clockwise) {
        if (!canRotate(clockwise)) {
            return;
        }

        orientation = orientation.rotate(clockwise);
    }

    /**
     * Returns the block in the specified (x,&nbsp;y) position, or null if the
     * position is empty. The result depends on the current orientation, since
     * the configuration of blocks is different for each orientation. If the x-
     * or y-coordinate is out of range, returns null.
     *
     * @param x x-coordinate of the element to return
     * @param y y-coordinate of the element to return
     * @return the block at the specified position, or null if the position is
     * empty
     */
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

    /**
     * Disassembles the piece into independent blocks, and adds them to the
     * matrix. The blocks are added to the current position of the piece. After
     * this the piece will intersect with the matrix and should be respawned. It
     * should be noted that this method does not modify the piece in any way.
     */
    public void lock() {
        matrix.addPiece(this);
    }

    /**
     * Sets the next tetromino, and moves the piece into its initial position.
     *
     * @param next next tetromino
     * @return true if, after respawning, intersects with the matrix, false
     * otherwise
     */
    public boolean respawn(Tetromino next) {
        setTetromino(next);
        moveToInitialPosition();

        return !testCollision(orientation, x, y);
    }

    private void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
        orientation = Direction.UP;
    }

    private void moveToInitialPosition() {
        x = (matrix.getCols() - tetromino.width) / 2;
        y = 0;
    }

    /**
     * Returns the x-coordinate of the current position.
     *
     * @return x-coordinate of the current position
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the current position.
     *
     * @return y-coordinate of the current position.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of a tetromino, which is constant.
     *
     * @return width of a tetromino
     */
    public int getWidth() {
        return tetromino.width;
    }

    /**
     * Returns the height of a tetromino, which is constant.
     *
     * @return height of a tetromino
     */
    public int getHeight() {
        return tetromino.height;
    }

    /**
     * Returns the current tetromino.
     *
     * @return current tetromino
     */
    public Tetromino getTetromino() {
        return tetromino;
    }

    /**
     * Returns the current orientation.
     *
     * @return current orientation
     */
    public Direction getOrientation() {
        return orientation;
    }

}
