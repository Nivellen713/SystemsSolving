package SolvingMethods.DirectMethods;
import Assay.MatrixException;
import Matrix.*;

public class GaussMethod extends Solver {

    /** Поле d для хранения промежуточных результатов, используемых в нескольких методах
     *
     */
    private double d;

    public Matrix matrix;
    public LinearMatrix dopMatrix;
    public LinearMatrix answers;

    public GaussMethod(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        super(matrix, dopMatrix, answers);
        this.matrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;
    }


    public LinearMatrix solve() throws MatrixException {
        straightRun();
        reverse();
        return answers;
    }

    private void straightRun() throws MatrixException { // ПРЯМОЙ ХОД
        int n =  matrix.getHorizontalSize();
        for (int k = 0; k <= n - 1; k++){
            for (int j = k + 1; j <=  n - 1; j++){
                d = matrix.getElement(j, k) / matrix.getElement(k, k);    // Умножение k-й строки на число. (1)
                // Вычитание k-й строки из j-й строки
                for (int i = k; i <= n - 1; i++){
                    double elem = matrix.getElement(j, i) - d * matrix.getElement(k, i);   // (2)
                    matrix.setElement(j, i, elem);
                }
                double elem = dopMatrix.getElement(j) - d * dopMatrix.getElement(k);   // (3)
                dopMatrix.setElement(j, elem);
            }
        }
    }

    private void reverse() throws MatrixException { // ОБРАТНЫЙ ХОД
        int n =  matrix.getHorizontalSize();
        for (int k = n - 1; k >= 0; k--){
            d = 0;
            for (int j = k + 1; j <= n - 1; j++){
                double s = matrix.getElement(k, j) * answers.getElement(j);    // Вычисление неизвестных. (4)
                d += s; // (4)
            }
            double elem = (dopMatrix.getElement(k) - d) / matrix.getElement(k, k);   // (4)
            answers.setElement(k, elem);
        }
    }
}