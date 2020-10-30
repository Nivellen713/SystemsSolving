package SolvingMethods.DecompositionMethods;

import Assay.MatrixException;
import Matrix.*;

/**
 * Формулы для определения матрицы U.
 *      Элементы главной диагонали матрицы U:
 *          u_ii = [a_ij - sum{k=1,..,i-1}[u_ki^2]]^(1/2),    i=1,..,n        (1)
 *      Элементы матрицы U, отличные от главной диагонали:
 *          u_ij = [a_ij - sum{k=1,..,i-1}[u_ki * u_kj]] / u_ii,    j=2,..,n , j>1       (2)
 *                                                                  при j<1 u_ij=0
 * Формулы для решения приобразованной системы.
 *      Вычисление вектора вспомогательных неизвестных Y.
 *          y_i = (f_i - sum{k=1,..,i-1}[l_ik * y_k]) / u_ii,   i=1,2,..,n     (3)
 *      Решение системы Ux = y.
 *          x_i = [y_i - sum{k=i+1,..,n}[u_ik * x_k]]) / u_ii,   i=n-1,n-2,..,1     (4)
 */

public class SquareRootMethod extends Solver {

    private Matrix matrix;
    private LinearMatrix dopMatrix;
    private LinearMatrix answers;

    /**
     * Поле matrixU нижняя треугольная матрица
     * Поле matrixUtran есть транспонированная matrixU
     */

    private Matrix matrixU;
    private Matrix matrixUtran;

    public SquareRootMethod(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        super(matrix, dopMatrix, answers);
        this.matrix = matrix;
        this.dopMatrix = dopMatrix;
        this.answers = answers;
        try {
            matrixU = new Matrix(answers.getSize(), answers.getSize());
            matrixUtran = new Matrix(answers.getSize(), answers.getSize());
        } catch (MatrixException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void decomposition() {

        try {
            for (int i = 0; i < matrix.getVerticalSize(); i++) {
                for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                    double sum = 0;
                    if (i == j) {
                        for (int k = 0; k < i - 1; k++) {
                            sum += matrixU.getElement(k, i) * matrixU.getElement(k, i);      // формула (1)
                        }
                        double value = matrix.getElement(i, i) - sum;      // формула (1)
                        value = Math.sqrt(value);      // формула (1)
                        matrixU.setElement(i, i, value);      // формула (1)
                    } else if (i < j) {
                        for (int k = 0; k < i - 1; k++) {
                            sum += matrixU.getElement(k, i) * matrixU.getElement(k, j);      // формула (2)
                        }
                        double value = matrix.getElement(i, j) - sum;      // формула (2)
                        value /= matrixU.getElement(i, i);      // формула (2)
                        matrixU.setElement(i, j, value);      // формула (2)
                    } else {
                        matrixU.setElement(i, j, 0);
                    }
                }
            }
        } catch (MatrixException e) {
            e.printStackTrace();
        }
    }


    @Override
    public LinearMatrix solve() throws MatrixException {

        decomposition();

        System.out.println("U : " + matrixU);
        matrixUtran = new Calculate().transposition(matrixU);
        System.out.println("U tran : " + matrixUtran);
        System.out.println("U * U tran : " + new Calculate().multiply(matrixUtran, matrixU));


        LinearMatrix y = new LinearMatrix(answers.getSize());

        for (int i = 0; i < y.getSize(); i++) {
            double sum = 0;
            for (int k = 0; k <= i - 1; k++) {
                sum += matrixU.getElement(k, i) * y.getElement(k);      // формула (3)
            }
            double value = dopMatrix.getElement(i) - sum;      // формула (3)
            value /= matrixU.getElement(i, i);      // формула (3)
            y.setElement(i, value);      // формула (3)
        }

        System.out.println("Y " + y);

        for (int i = y.getSize() - 1; i >= 0; i--) {
            double sum = 0;
            for (int k = i + 1; k < answers.getSize(); k++) {
                sum += matrixU.getElement(i, k) * answers.getElement(k);      // формула (4)
            }
            double value = y.getElement(i) - sum;      // формула (4)
            value /= matrixU.getElement(i,i);      // формула (4)
            answers.setElement(i, value);      // формула (4)
        }

        return answers;
    }

}
