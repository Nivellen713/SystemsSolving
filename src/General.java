import java.util.Scanner;

class General {

    void showMatrix (double[][] testMatrix, double[] dopMatrix) {
        System.out.println('\n' + "Матрица вашей системы алгебраических уравнений, милорд:");
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(testMatrix[i][j] + "  ");
                if (j == 2){
                    System.out.print("\t|\t" + dopMatrix[i]);
                }
            }
            System.out.println();
        }
    }

    double determinant(double[][] matrix) {
        int size = matrix.length;
        if(size == 1) return matrix[0][0];
        double det = 0;
        double[][] dopMatrix = new double[size-1][size-1];
        int l = 1;
        for(int i = 0; i < size; ++i){
            int x = 0, y = 0;
            for(int j = 1; j < size; ++j) {
                for(int k = 0; k < size; ++k) {
                    if(i == k) continue;
                    dopMatrix[x][y] = matrix[j][k];
                    ++y;
                    if(y == size - 1) {
                        y = 0;
                        ++x;
                    }
                }
            }
            det += l * matrix[0][i] * determinant(dopMatrix);
            l *= (-1);
        }
        return det;
    }

}
