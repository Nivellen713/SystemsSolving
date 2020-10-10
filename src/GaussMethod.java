import java.util.Scanner;

public class GaussMethod {
    public static void main(String[] args) {

        General general = new General();
        Scanner scanner = new Scanner(System.in);
        double det;
        int amountOfUnknowns;
        double d, s;

        //------------------------- Вводим матрицу -------------------------//

        System.out.println("Введите количество неизвестных: ");
        amountOfUnknowns = scanner.nextInt();
        double[][] testMatrix = new double[amountOfUnknowns][amountOfUnknowns];
        double[] dopMatrix = new double[amountOfUnknowns];
        double[] x = new double[amountOfUnknowns];

        for (int j = 1; j <= amountOfUnknowns ; j++) {
            System.out.println("Введите " + j + " строку: ");
            for (int i = 0; i < amountOfUnknowns; i++){
                testMatrix[i][j - 1] = scanner.nextInt();
            }
        }

        System.out.println("Введите свободные члены: ");
        for (int i = 0; i < amountOfUnknowns; i++){
            dopMatrix[i] = scanner.nextInt();
        }

        System.out.println('\n' + "Определитель матрицы: ");
        det = general.determinant(testMatrix);
        System.out.println("determinant = " + det);

        //------------- ПРЯМОЙ ХОД -------------//
        for (int k = 0; k <= amountOfUnknowns - 1; k++){
            for (int j = k + 1; j <= amountOfUnknowns - 1; j++){
                d = testMatrix[j][k] / testMatrix[k][k];    // Умножение k-й строки на число. (1)
                // Вычитание k-й строки из j-й строки
                for (int i = k; i <= amountOfUnknowns - 1; i++){
                    testMatrix[j][i] -= d * testMatrix[k][i];   // (2)
                }
                dopMatrix[j] -= d * dopMatrix[k];   // (3)
            }
        }
            //------------- ОБРАТНЫЙ ХОД -------------//
            for (int k = amountOfUnknowns - 1; k >= 0; k--){
                d = 0;
                for (int j = k + 1; j <= amountOfUnknowns - 1; j++){
                    s = testMatrix[k][j] * x[j];    // Вычисление неизвестных. (4)
                    d += s; // (4)
                }
                x[k] = (dopMatrix[k] - d) / testMatrix[k][k];   // (4)
        }
        System.out.println("\nРешение:");
        for (double v : x) {
            System.out.print(v + " ");
        }
    }
}