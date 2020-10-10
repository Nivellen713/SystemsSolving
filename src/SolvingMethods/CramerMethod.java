//package SolvingMethods;
//
//import java.util.Scanner;
//public class CramerMethod {
//
//    static void returnValues (double[][] matrix, double[][] rememberMatrix){
//        for (int i = 0; i < 3; i++){
//            for (int j = 0; j < 3; j++){
//                matrix[i][j] = rememberMatrix[i][j];
//            }
//        }
//    }
//
//    //---------------- Вычисляем детерминант матрицы с заменой --------------//
//
//    public static double detOfElements(double[][] matrix, double[] dopMatrix, char detCoeff){
//        General general = new General();
//        switch (detCoeff){
//            case 'x':
//                for (int i = 0; i < dopMatrix.length; i++){
//                    matrix[i][0] = dopMatrix[i];
//                }
//                    return general.determinant(matrix);
//
//            case 'y':
//                for (int i = 0; i < dopMatrix.length; i++){
//                    matrix[i][1] = dopMatrix[i];
//                }
//                    return general.determinant(matrix);
//
//            case 'z':
//                for (int i = 0; i < dopMatrix.length; i++){
//                    matrix[i][2] = dopMatrix[i];
//                }
//                    return general.determinant(matrix);
//
//            default:
//                return 0;
//        }
//    }
//
//    public static void main(String[] args) {
//
//        General general = new General();
//        Scanner scanner = new Scanner(System.in);
//
//        double[][] testMatrix = new double[3][3]; // коэффициенты при неизвестных
//        double[][] rememberMatrix = new double[3][3];
//        double[] dopMatrix = new double[3];   // свободные члены СЛАУ
//
//        double deltaX;
//        double deltaY;
//        double deltaZ;
//        double deltaAll;
//
//        double x;
//        double y;
//        double z;
//
//        //------------------------- Вводим матрицу -------------------------//
//
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
//
//        //----------------------- Вывод введённых значений -----------------------//
//
//        general.showMatrix(testMatrix, dopMatrix);
//
//        System.out.println('\n' + "Определитель вашей матрицы, сир: ");
//        deltaAll = general.determinant(testMatrix);
//        System.out.println("determinant = " + deltaAll);
//
//        deltaX = detOfElements(testMatrix, dopMatrix, 'x');
//            returnValues(testMatrix, rememberMatrix);
//        deltaY = detOfElements(testMatrix, dopMatrix, 'y');
//            returnValues(testMatrix, rememberMatrix);
//        deltaZ = detOfElements(testMatrix, dopMatrix, 'z');
//            returnValues(testMatrix, rememberMatrix);
//
//        System.out.println('\n' + "Ваши определители по свободным членам, господин: ");
//        System.out.println("deltaX = " + deltaX);
//        System.out.println("deltaY = " + deltaY);
//        System.out.println("deltaZ = " + deltaZ);
//
//        x = deltaX / deltaAll;
//        y = deltaY / deltaAll;
//        z = deltaZ / deltaAll;
//
//        System.out.println('\n' + "Милорд, ответы готовы: ");
//
//        System.out.println("x = " + x);
//        System.out.println("y = " + y);
//        System.out.println("z = " + z);
//    }
//}