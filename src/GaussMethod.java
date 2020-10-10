import java.util.Scanner;

public class GaussMethod {
    public static void main(String[] args) {

        General general = new General();
        Scanner scanner = new Scanner(System.in);

        double[][] testMatrix = {{1, -1, 3, 1}, {4, -1, 5, 4}, {2, -2, 4, 1}, {1, -4, 5, -1}}; // коэффициенты при неизвестных
        double[][] rememberMatrix = new double[4][4];
        double[] dopMatrix = {5, 4, 6, 3};   // свободные члены СЛАУ

        double det;
        double element;
        int n = 3;

        double[] x = new double[dopMatrix.length];
        double d, s;

        //------------------------- Вводим матрицу -------------------------//

//        System.out.println("Введите коэффициенты при x");
//        for (int i = 0; i < 3; i++){
//            testMatrix[i][0] = scanner.nextInt();
//            rememberMatrix[i][0] = testMatrix[i][0];
//        }
//
//        System.out.println("Введите коэффициенты при y");
//        for (int i = 0; i < 3; i++){
//            testMatrix[i][1] = scanner.nextInt();
//            rememberMatrix[i][1] = testMatrix[i][1];
//        }
//
//        System.out.println("Введите коэффициенты при z");
//        for (int i = 0; i < 3; i++){
//            testMatrix[i][2] = scanner.nextInt();
//            rememberMatrix[i][2] = testMatrix[i][2];
//        }
//
//        System.out.println("Введите свободные члены");
//        for (int i = 0; i < 3; i++){
//            dopMatrix[i] = scanner.nextInt();
//        }

        System.out.println('\n' + "Определитель вашей матрицы, сир: ");
        det = general.determinant(testMatrix);
        System.out.println("determinant = " + det);

        //------------- ПРЯМОЙ ХОД -------------//
        for (int k = 0; k <= n; k++){
            for (int j = k + 1; j <= n; j++){
                d = testMatrix[j][k] / testMatrix[k][k];    // Умножение k-й строки на число
                // Вычитание k-й строки из j-й строки
                for (int i = k; i <= n; i++){
                    testMatrix[j][i] -= d * testMatrix[k][i];
                }
                dopMatrix[j] -= d * dopMatrix[k];
            }
        }
            //------------- ОБРАТНЫЙ ХОД -------------//
            for (int k = n; k >= 0; k--){
                d = 0;
                for (int j = k + 1; j <= n; j++){
                    s = testMatrix[k][j] * x[j];    // Вычисление неизвестных
                    d += s;
                }
                x[k] = (dopMatrix[k] - d) / testMatrix[k][k];
        }
        System.out.println("\nРешение:");
        for (double v : x) {
            System.out.print(v + " ");
        }
    }
}
