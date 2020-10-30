package SolvingMethods.DecompositionMethods;

import Assay.MatrixException;
import Matrix.LinearMatrix;
import Matrix.Matrix;

public class RotationMethod extends Solver {
    public RotationMethod(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        super(matrix, dopMatrix, answers);
        try {
            matrixU = new Matrix(answers.getSize(), answers.getSize());
        } catch (MatrixException ex) {
            ex.getMessage();
        }
    }

/**
 * Поле matrixU используется для хранния истории преобразований исходной матрицы matrix
 **/
    private Matrix matrixU;

    private double coeffC;
    private double coeffS;

    @Override
    void decomposition() throws MatrixException {

        coeffC = matrix.getElement(0, 0);
        coeffC /= Math.sqrt(Math.pow(matrix.getElement(0, 0), 2) + Math.pow(matrix.getElement(1, 0), 2));

        coeffS = matrix.getElement(1, 0);
        coeffS /= Math.sqrt(Math.pow(matrix.getElement(0, 0), 2) + Math.pow(matrix.getElement(1, 0), 2));

        for (int i = 0; i < matrix.getHorizontalSize(); i++){
            double value = coeffC * matrix.getElement(0, i) + coeffS * matrix.getElement(1, i);
            matrix.setElement(0, i, value);
            value = coeffC * dopMatrix.getElement(0);
            for (int j = 1; j < matrix.getHorizontalSize(); j++){
                value = -coeffS * matrix.getElement(i, j) + coeffC * matrix.getElement(i, j);
                matrix.setElement(i + 1, j, value);

                coeffC = matrix.getElement(0, 0);
                coeffC /= Math.sqrt(Math.pow(matrix.getElement(0, 0), 2) + Math.pow(matrix.getElement(1, 0), 2));

                coeffS = matrix.getElement(i + 1, 0);
                coeffS /= Math.sqrt(Math.pow(matrix.getElement(0, 0), 2) + Math.pow(matrix.getElement(1, 0), 2));

            }
        }

    }


    @Override
    public LinearMatrix solve() throws MatrixException {
        return null;
    }
}
