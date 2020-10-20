package SolvingMethods;

import Assay.MatrixException;
import Matrix.*;

/**
 * Формулы для LU-разложения исходной матрицы.
 *      Заполнение матрицы U:
 *          u_ij = a_ij,    j=1,..,n        (1)
 *      Заполнение матрицы L:
 *          l_ij = a_j1 / u_11, j=2,..,n      (2)
 *      Для i = 2,...,n формулы (1) и (2) принимают вид:
 *          u_ij = a_ij - sum{k=1,..,i}[l_ik * u_kj],   j=i,..,n     (3)
 *          l_ji = (1/u_ii) * (a_ji - sum{k=1,..,i}[l_jk * u_ki],   j=i+1,..,n        (4)
 *
 * Формулы для решения приобразованной системы.
 *      Решение системы Ly = f, вычисление вектора решения y.
 *          y_1 = f_1 / l_11        (5)
 *          y_i = (1/l_ii) * (f_i - sum{k=1,..,i-1}[l_ik * y_k]),   i=2,3,..,n     (6)
 *      Решение системы Ux = y.
 *          x_n = y_n       (7)
 *          x_i = y_i - sum{k=i+1,..,n}[u_ik * x_k]),   i=n-1,n-2,..,1     (8)
 */

public class LowerUpper extends Solver {

    /**
     * Поле lMatrix Сохраняет историю нормировок и вычитаний в процессе исключения неизвестных по методу Гаусса
     */
    private Matrix matrixL;

    /**
     * Поле uMatrix представляет эквивалентный вид исходной системы, который она приобретает по завершению процесса исключения
     */
    private Matrix matrixU;


    public LowerUpper(Matrix matrix, LinearMatrix dopMatrix, LinearMatrix answers) {
        super(matrix, dopMatrix, answers);
        try {
            matrixL = new Matrix(answers.getSize(), answers.getSize());
            matrixU = new Matrix(answers.getSize(), answers.getSize());
        } catch (MatrixException ex) {
            ex.getMessage();
        }
    }

    public LinearMatrix solve() throws MatrixException {

        decomposition();

        LinearMatrix y = new LinearMatrix(answers.getSize());

        y.setElement(0,
                dopMatrix.getElement(0) / matrixL.getElement(0, 0));      // формула (5)


        for (int i = 1; i < y.getSize(); i++) {
            double sum = 0;
            for (int j = 0; j < i - 1; j++) {
                // Прямая подстановка. Находим вектор решения y
                sum += matrixL.getElement(i, j) * y.getElement(j);      // формула (6)
            }
            y.setElement(i,
                    (dopMatrix.getElement(i) - sum) / matrixL.getElement(i, i));        // формула (6)
        }

        answers.setElement(answers.getSize() - 1, y.getElement(y.getSize() - 1));      // формула (7)
        for (int i = y.getSize() - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < answers.getSize(); j++) {
                sum += matrixU.getElement(i, j) * answers.getElement(j);      // формула (8)
            }
            answers.setElement(i, y.getElement(i) - sum);       // формула (8)
        }

        return answers;
    }

    private void decomposition() throws MatrixException {

        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getVerticalSize(); j++) {
                matrixU.setElement(0, i,
                        matrix.getElement(0, i));   // формула (1)
                matrixL.setElement(i, 0,
                        matrix.getElement(i, 0) / matrixU.getElement(0, 0));      // формула (2)

                double sum = 0;

                for (int k = 0; k < i; k++) {
                    sum += matrixL.getElement(i, k) * matrixU.getElement(k, j);      // формула (3)
                }
                matrixU.setElement(i, j, matrix.getElement(i, j) - sum);      // формула (3)

                if (i > j) {
                    matrixL.setElement(j, i, 0);
                } else {
                    sum = 0;
                    for (int k = 0; k < i; k++) {
                        sum += matrixL.getElement(j, k) * matrixU.getElement(k, i);      // формула (4)
                    }
                    matrixL.setElement(j, i,
                            (matrix.getElement(j, i) - sum) / matrixU.getElement(i, i));      // формула (4)
                }
            }
        }
    }
}
