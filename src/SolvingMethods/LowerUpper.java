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
 *          y_i = (1/l_ii) * (f_i - sum{k=1,..,i-1}[l_ik * y_k]),   i=1,2,..,n     (5)
 *      Решение системы Ux = y.
 *          x_i = (y_i - sum{k=i+1,..,n}[u_ik * x_k])) / u_kk,   i=n-1,n-2,..,1     (6)
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

        System.out.println("L * U : " + new Calculate().multiply(matrixL, matrixU));

        LinearMatrix y = new LinearMatrix(answers.getSize());

        for (int i = 0; i < y.getSize(); i++) {
            double sum = 0;
            for (int j = 0; j <= i - 1; j++) {
                // Прямая подстановка. Находим вектор решения y
                double a = matrixL.getElement(i, j);
                double b = y.getElement(j);
                sum += a * b;      // формула (5)
            }
            double value = (dopMatrix.getElement(i) - sum) / matrixL.getElement(i, i);      // формула (5)
            y.setElement(i, value);
        }

        System.out.println("L " + matrixL);
        System.out.println("Y " + y);
        System.out.println("\tCorrect Y: " + "5,00 -16,0 -4,00 -8,00");

        for (int i = y.getSize() - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < answers.getSize(); j++) {
                sum += matrixU.getElement(i, j) * answers.getElement(j);      // формула (6)
            }
            answers.setElement(i, (y.getElement(i) - sum) / matrixU.getElement(i,i));       // формула (6)
        }

        System.out.println("U " + matrixU);

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
