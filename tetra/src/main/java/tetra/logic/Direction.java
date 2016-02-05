package tetra.logic;

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
        UP.dy = 1;
        RIGHT.dx = 1;
        RIGHT.dy = 0;
        DOWN.dx = 0;
        DOWN.dy = -1;
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

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }

    public Direction rotateClockwise() {
        return nextClockwise;
    }

    public Direction rotateCounterclockwise() {
        return nextCounterclockwise;
    }

    public Direction rotate(boolean clockwise) {
        return clockwise ? nextClockwise : nextCounterclockwise;
    }

}
