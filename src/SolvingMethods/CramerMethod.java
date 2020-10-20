package SolvingMethods;
import Assay.MatrixException;
import Matrix.*;

public class CramerMethod extends Solver{

    private Matrix rememberMatrix;
    private double[] deltas;

    public CramerMethod(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        super(matrix, dopMatrix, answers);
    }

    //---------------- Вычисляем детерминант матрицы с заменой --------------//

    private double detOfElements(int k) throws MatrixException {
        for (int i = 0; i < dopMatrix.getSize(); i++) {
            matrix.setElement(i, k, dopMatrix.getElement(i));
        }
        return new Calculate().determinant(matrix);
    }

    private void detOfMinors() throws MatrixException {
        for (int i = 0; i < deltas.length; i++) {
            double det = detOfElements(i);
            deltas[i] = det;
            matrix = new Calculate().returnValues(matrix, rememberMatrix);
        }
    }

    public LinearMatrix solve() throws MatrixException {
        try {
            rememberMatrix = new Matrix(dopMatrix.getSize(), dopMatrix.getSize());
            rememberMatrix = new Calculate().returnValues(rememberMatrix, matrix);
        } catch (MatrixException ex){
            ex.getMessage();
        }
        this.deltas = new double[dopMatrix.getSize()];

        detOfMinors();
        double det = new Calculate().determinant(matrix);
        for (int i = 0; i < answers.getSize(); i++) {
            double value = deltas[i] / det;
            answers.setElement(i, value);
        }
        return answers;
    }
}