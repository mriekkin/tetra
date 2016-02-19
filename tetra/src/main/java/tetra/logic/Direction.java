package tetra.logic;

/**
 * Perpendicular directions in the plane. Tetris operates in a cartesian
 * coordinate system where the possible translations are up, down, left and
 * right. These are also the only possible orientations since rotations are
 * constrained at 90&deg; angles.
 */
public enum Direction {

    UP, RIGHT, DOWN, LEFT;

    static {
        setTranslations();
        setRotations();
    }

    private int dx;
    private int dy;
    private Direction nextClockwise;
    private Direction nextCounterclockwise;

    private static void setTranslations() {
        UP.dx = 0;
        UP.dy = -1;
        RIGHT.dx = 1;
        RIGHT.dy = 0;
        DOWN.dx = 0;
        DOWN.dy = 1;
        LEFT.dx = -1;
        LEFT.dy = 0;
    }

    private static void setRotations() {
        Direction[] directions = values();
        for (int i = 0; i + 1 < directions.length; i++) {
            directions[i].nextClockwise = directions[i + 1];
            directions[i + 1].nextCounterclockwise = directions[i];
        }

        int last = directions.length - 1;
        directions[last].nextClockwise = directions[0];
        directions[0].nextCounterclockwise = directions[last];
    }

    /**
     * Returns the horizontal translation required for one step in this
     * direction. This would be -1 for left, and +1 for right.
     *
     * @return horizontal translation
     */
    public int dx() {
        return dx;
    }

    /**
     * Returns the vertical translation required for one step in this direction.
     * Since the vertical axis is inverted, this would -1 for up, and +1 for
     * down.
     *
     * @return vertical translation
     */
    public int dy() {
        return dy;
    }

    /**
     * Returns the next available orientation when turning clockwise.
     *
     * @return next orientation when turning clockwise
     */
    public Direction rotateClockwise() {
        return nextClockwise;
    }

    /**
     * Returns the next available orientation when turning counterclockwise.
     *
     * @return next orientation when turning counterclockwise
     */
    public Direction rotateCounterclockwise() {
        return nextCounterclockwise;
    }

    /**
     * Returns the next available orientation when turning either clockwise or
     * counterclockwise.
     *
     * @param clockwise true for clockwise, false for counterclockwise
     * @return next orientation
     */
    public Direction rotate(boolean clockwise) {
        return clockwise ? nextClockwise : nextCounterclockwise;
    }

}
