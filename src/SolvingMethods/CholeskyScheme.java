//package SolvingMethods;
//
//import java.util.Scanner;
//
//public class CholeskyScheme {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        double[][] generalMatrix = new double[3][3]; // коэффициенты при неизвестных
//        double[][] rememberMatrix = new double[3][3];
//
//        double[] dopMatrix = new double[3];   // свободные члены СЛАУ
//        double[][] u = new double[3][3];
//
//        double[]y = new double[3];  // массив вспомогательных членов y
//        double[]x = new double[3];  // массив искомых значений x
//
//
//        //------------------------- Ввод рабочий матрицы (СЛАУ) -------------------------//
////
////        System.out.println("Введите коэффициенты при x");
////        for (int i = 0; i < 3; i++) {
////            generalMatrix[i][0] = scanner.nextDouble();
////            rememberMatrix[i][0] = generalMatrix[i][0];
////        }
////
////        System.out.println("Введите коэффициенты при y");
////        for (int i = 0; i < 3; i++) {
////            generalMatrix[i][1] = scanner.nextDouble();
////            rememberMatrix[i][1] = generalMatrix[i][1];
////        }
////
////        System.out.println("Введите коэффициенты при z");
////        for (int i = 0; i < 3; i++) {
////            generalMatrix[i][2] = scanner.nextDouble();
////            rememberMatrix[i][2] = generalMatrix[i][2];
////        }
////
////        System.out.println("Введите свободные члены");
////        for (int i = 0; i < 3; i++) {
////            dopMatrix[i] = scanner.nextDouble();
////        }
////
////        System.out.println('\n');
//
//        generalMatrix[0][0] = 2.0;
//        generalMatrix[0][1] = -0.45;
//        generalMatrix[0][2] = 1.6;
//
//        generalMatrix[1][0] = -0.45;
//        generalMatrix[1][1] = 5;
//        generalMatrix[1][2] = -2;
//
//        generalMatrix[2][0] = 1.6;
//        generalMatrix[2][1] = -2;
//        generalMatrix[2][2] = 10;
//
//        dopMatrix[0] = -3;
//        dopMatrix[1] = 1;
//        dopMatrix[2] = 0;
//
//        //----------------------- Вывод введённых значений -----------------------//
//
//        new General().showMatrix(generalMatrix, dopMatrix);
//
//        //----------------------- Поиск коэффициентов при u -----------------------//
//
//        u[0][0] = Math.sqrt(generalMatrix[0][0]);
//        for(int j = 1; j < 3; j++) {
//            u[0][j] = generalMatrix[0][j] / u[0][0];
//        }
//
//        u[1][1] = Math.sqrt(generalMatrix[1][1] - Math.pow(u[0][1], 2));
//
//        u[1][2] = (generalMatrix[1][2] - u[0][1]*u[0][2]) / u[1][1];
//
//        u[2][2] = Math.sqrt(generalMatrix[2][2] - (Math.pow(u[0][2],2) + Math.pow(u[1][2],2)));
//
//
//        //----------------------- Поиск вспомогательных неизвестных y -----------------------//
//
//        y[0] = dopMatrix[0] / u[0][0];
//        y[1] = (dopMatrix[1] - u[0][1] * y[0]) / u[1][1];
//        y[2] = (u[0][2]*y[0] - u[1][2]*y[1]) / u[2][2];
//
//        //----------------------- Поиск ответов -----------------------//
//
//        x[2] = y[2] / u[2][2];
//        x[1] = (y[1] - u[1][2] * x[2]) / u[1][1];
//        x[0] = (y[0] - (u[0][1] * x[1] + u[0][2] * x[2])) / u[0][0];
//
//
//        //----------------------- Вывод результатов -----------------------//
//
//        System.out.println('\n');
//
//        System.out.println("u matrix: ");
//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){
//                System.out.print(u[i][j] + "  ");
//            }
//            System.out.println();
//        }
//
//        System.out.println('\n');
//
//        System.out.println("y: ");
//        for (int i = 0; i < 3; i++){
//            System.out.print(y[i]+ "  ");
//        }
//
//        System.out.println('\n');
//
//        System.out.println("x: ");
//        for (int i = 0; i < 3; i++){
//                System.out.print(x[i]+ "  ");
//            }
//
//
//    }
//}