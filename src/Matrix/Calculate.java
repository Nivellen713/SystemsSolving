package Matrix;

import Assay.MatrixException;

public class Calculate {

    public double determinant(Matrix matrix) throws MatrixException {
        int size = matrix.getHorizontalSize();
        if (size == 1) return matrix.getElement(0, 0);
        double det = 0;
        Matrix dopMatrix = new Matrix(size - 1, size - 1);
        int l = 1;
        for (int i = 0; i < size; ++i) {
            int x = 0, y = 0;
            for (int j = 1; j < size; ++j) {
                for (int k = 0; k < size; ++k) {
                    if (i == k) continue;
                    dopMatrix.setElement(x, y, matrix.getElement(j, k));
                    ++y;
                    if (y == size - 1) {
                        y = 0;
                        ++x;
                    }
                }
            }
            det += l * matrix.getElement(0, i) * determinant(dopMatrix);
            l *= (-1);
        }
        return det;
    }

    public Matrix multiply(Matrix matrixFirst, Matrix matrixSecond) throws MatrixException {
        Matrix multipliedMatrix = new Matrix(
                matrixFirst.getHorizontalSize(), matrixFirst.getVerticalSize());
        for (int i = 0; i < multipliedMatrix.getVerticalSize(); i++) {
            for (int j = 0; j < multipliedMatrix.getHorizontalSize(); j++) {
                double a = 0;
                for (int k = 0; k < multipliedMatrix.getHorizontalSize(); k++) {
                    a += matrixFirst.getElement(i, k) * matrixSecond.getElement(k, j);
                }
                multipliedMatrix.setElement(i, j, a);
            }
        }
        return multipliedMatrix;
    }

    public Matrix transposition(Matrix matrix) throws MatrixException {
        Matrix transpositionMatrix = new Matrix(matrix.getHorizontalSize(), matrix.getVerticalSize());
        for (int i = 0; i < matrix.getHorizontalSize(); i++) {
            for (int j = 0; j < matrix.getVerticalSize(); j++) {
                transpositionMatrix.setElement(j, i, matrix.getElement(i, j));
            }
        }
        return transpositionMatrix;
    }

    public Matrix returnValues(Matrix to, Matrix from) {
        try {
            for (int i = 0; i < to.getHorizontalSize(); i++) {
                for (int j = 0; j < to.getVerticalSize(); j++) {
                    to.setElement(i, j, from.getElement(i, j));
                }
            }
        } catch (MatrixException ex) {
            System.out.println(ex.getMessage());
        }
        return to;
    }
}
