package Matrix;

public class Matrix {
    private double[][] matrix;
    public Matrix(int line, int column) throws MatrixException {
        // Проверяем на отрацительные значения матрицы
        if ((line < 1) || (column < 1)) {
            throw new MatrixException();
        }
        matrix = new double[line][column];
    }
    public int getVerticalSize() {
        return matrix.length;
    }
    public int getHorizontalSize() {
        return matrix[0].length;
    }


    public double getElement(int i, int j) throws MatrixException {
        if (checkRange(i, j)) {
            return matrix[i][j];
        }
        throw new MatrixException();
    }
    public void setElement(int i, int j, double value) throws MatrixException {
        if (checkRange(i, j)) matrix[i][j] = value;
        else throw new MatrixException();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : " + matrix.length + "x" + matrix[0].length + "\n");
        for (double [] row : matrix){
            for (double value : row) {
                s.append(value + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // проверка возможности выхода за пределы матрицы
    private boolean checkRange(int i, int j){
        if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length){
            return true;
        } else {
            return false;
        }
    }
}
