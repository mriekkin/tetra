package tetra.logic;

public class MatrixHelper {

    public static Matrix getFullMatrix(int width, int height, Block b) {
        Matrix matrix = new Matrix(height, width);
        for (int y = 0; y < matrix.getRows(); y++) {
            fillRow(matrix, y, b);
        }

        return matrix;
    }

    public static void fillRow(Matrix matrix, int y, Block b) {
        for (int x = 0; x < matrix.getCols(); x++) {
            matrix.setBlock(x, y, b);
        }
    }

    static public boolean areCompleteLines(LineClearer lineClearer, int yMin, int yMax) {
        for (int y = yMin; y <= yMax; y++) {
            if (!lineClearer.isCompleteLine(y)) {
                return false;
            }
        }

        return true;
    }

    public boolean areEmptyLines(LineClearer lineClearer, int yMin, int yMax) {
        for (int y = yMin; y <= yMax; y++) {
            if (!lineClearer.isEmptyLine(y)) {
                return false;
            }
        }

        return true;
    }

}
