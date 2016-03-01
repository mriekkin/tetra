package tetra.logic;

/**
 * Finds and clears complete lines in a matrix.
 */
public class LineClearer {

    private Matrix matrix;

    public LineClearer(Matrix matrix) {
        this.matrix = matrix;
    }

    public int clearCompleteLinesAndShift(int yMin, int yMax) {
        int first = findFirstCompleteLine(yMin, yMax);
        int last = findLastCompleteLine(yMin, yMax);
        int count = last - first + 1;

        if (first == -1) {
            return 0;
        }

        clearLines(first, last);
        shiftRowsDown(first - 1, count);

        return count;
    }

    public boolean isCompleteLine(int y) {
        for (int x = 0; x < matrix.getCols(); x++) {
            if (!matrix.isOccupied(x, y)) {
                return false;
            }
        }

        return true;
    }

    public boolean isEmptyLine(int y) {
        for (int x = 0; x < matrix.getCols(); x++) {
            if (matrix.isOccupied(x, y)) {
                return false;
            }
        }

        return true;
    }

    public int findFirstCompleteLine(int yMin, int yMax) {
        if (yMin > yMax) {
            return -1;
        }

        for (int y = yMin; y <= yMax; y++) {
            if (isCompleteLine(y)) {
                return y;
            }
        }

        return -1;
    }

    public int findLastCompleteLine(int yMin, int yMax) {
        if (yMin > yMax) {
            return -1;
        }

        for (int y = yMax; y >= yMin; y--) {
            if (isCompleteLine(y)) {
                return y;
            }
        }

        return -1;
    }

    public void clearLines(int first, int last) {
        if (first > last) {
            return;
        }

        for (int y = last; y >= first; y--) {
            for (int x = 0; x < matrix.getCols(); x++) {
                matrix.setBlock(x, y, null);
            }
        }
    }

    public void shiftRowsDown(int lastRowToMove, int howMuch) {
        if (lastRowToMove < 0 || lastRowToMove >= matrix.getRows()) {
            return;
        }

        if (howMuch <= 0 || lastRowToMove + howMuch >= matrix.getRows()) {
            return;
        }

        for (int y = lastRowToMove; y >= 0; y--) {
            for (int x = 0; x < matrix.getCols(); x++) {
                matrix.setBlock(x, y + howMuch, matrix.getBlock(x, y));
            }
        }

        clearLines(0, howMuch - 1);
    }

}
