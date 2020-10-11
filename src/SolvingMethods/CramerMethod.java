package SolvingMethods;

import Matrix.*;

public class CramerMethod {

    private Matrix matrix;
    private Matrix rememberMatrix;
    private LinearMatrix dopMatrix;
    private LinearMatrix deltas;
    private LinearMatrix answers;

    public CramerMethod(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        this.matrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;

        this.rememberMatrix = matrix;
        try {
            this.deltas = new LinearMatrix(answers.getSize());
        } catch (MatrixException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //---------------- Вычисляем детерминант матрицы с заменой --------------//
    private double detOfElements(int k) throws MatrixException {
        for (int i = 0; i < dopMatrix.getSize(); i++) {
            matrix.setElement(i, k, dopMatrix.getElement(i));
        }
        return new Calculate().determinant(matrix);
    }

    private void detOfMinors() throws MatrixException {
        for (int i = 0; i < deltas.getSize(); i++) {
            double det = detOfElements(i);
            deltas.setElement(i, det);
            new Calculate().returnValues(matrix, rememberMatrix);
        }
    }

    public LinearMatrix solving() throws MatrixException {
        detOfMinors();
        double det = new Calculate().determinant(matrix);
        for (int i = 0; i < answers.getSize(); i++) {
            answers.setElement(i, deltas.getElement(i) / det);
        }
        return answers;
    }
}