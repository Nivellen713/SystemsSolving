import Matrix.*;
import SolvingMethods.*;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the degree of the matrix: ");
//        int n = scanner.nextInt();
        int n = 4;
        try {
            Matrix matrix = new Matrix(n, n);
            LinearMatrix dopMatrix = new LinearMatrix(n);
            LinearMatrix answers = new LinearMatrix(n);

            new MatrixCreator().personalVariables(matrix, dopMatrix);
            System.out.println("Your matrix: " + matrix);
            System.out.println("Dop matrix: " + dopMatrix);
            matrix.setElement(0, 0, 1);
            matrix.setElement(0, 1, -1);
            matrix.setElement(0, 2, 3);
            matrix.setElement(0, 3, 1);

            matrix.setElement(1, 0, 4);
            matrix.setElement(1, 1, -1);
            matrix.setElement(1, 2, 5);
            matrix.setElement(1, 3, 4);

            matrix.setElement(2, 0, 2);
            matrix.setElement(2, 1, -2);
            matrix.setElement(2, 2, 4);
            matrix.setElement(2, 3, 1);

            matrix.setElement(3, 0, 1);
            matrix.setElement(3, 1, -4);
            matrix.setElement(3, 2, 5);
            matrix.setElement(3, 3, -1);

            dopMatrix.setElement(0, 5);
            dopMatrix.setElement(1, 4);
            dopMatrix.setElement(2, 6);
            dopMatrix.setElement(3, 3);

//            System.out.println("\nChoose a solution method.\nCramer of Gauss");
//            String solutionMethod = scanner.next();
//            solutionMethod = solutionMethod.trim().toLowerCase();

//            if (solutionMethod.equals("cramer")) {
//                answers = new CramerMethod(matrix, dopMatrix, answers).solving();
//            } else if (solutionMethod.equals("gauss")) {
//                answers = new GaussMethod(matrix, dopMatrix, answers).solving();
//            } else {
//                System.out.println("i don't know such a method");
//            }

            new LowerUpper(matrix, dopMatrix, answers).solving();
            new Calculate().multiply(matrix, matrix, matrix);

            System.out.println("Solve: " + answers);
        } catch (MatrixException ex) {
            System.err.println("Error of creating matrix " + ex);
        }
    }
}
