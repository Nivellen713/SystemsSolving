import java.util.Scanner;

public class GaussMethod {
    public static void main(String[] args) {

        General general = new General();
        Scanner scanner = new Scanner(System.in);

        double[][] testMatrix = {{3, 2, -5}, {2, -1, 3}, {1, 2, -1}}; // коэффициенты при неизвестных
        double[][] rememberMatrix = new double[3][3];
        double[] dopMatrix = {-1, 13, 9};   // свободные члены СЛАУ

        double det;
        double element;

        double x;
        double y;
        double z;

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


        element = testMatrix[0][0];

        for (int i = 0; i < 3; i++){
            dopMatrix[i] /= element;
            for (int j = 0; j < 3; j++){
                testMatrix[i][j] /= element;
            }
        }

        for (int i = 0; i < 3; i++){
            testMatrix[1][i] += -testMatrix[1][i] * testMatrix[0][i];
        }
        dopMatrix[1] += -dopMatrix[1] * dopMatrix[0];

        element = testMatrix[1][1];

        for (int i = 0; i < 3; i++){
            testMatrix[1][i] /= element;
        }
        dopMatrix[1] /= element;




        for (int i = 0; i < 3; i++){
            testMatrix[2][i] += -testMatrix[2][i] * testMatrix[0][i];
        }
        dopMatrix[2] += -dopMatrix[2] * dopMatrix[0];

        element = testMatrix[1][2];

        for (int i = 0; i < 3; i++){
            testMatrix[2][i] /= element;
        }
        dopMatrix[2] /= element;


        element = testMatrix[2][2];

        for (int i = 0; i < 3; i++){
            testMatrix[2][i] /= element;
        }
        dopMatrix[2] /= element;

        general.showMatrix(testMatrix, dopMatrix);



    }
}
