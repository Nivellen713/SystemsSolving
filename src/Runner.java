import Matrix.*;
import SolvingMethods.*;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите порядок матрицы: ");
        int n = scanner.nextInt();
        try {
            Matrix matrix = new Matrix(n, n);
            LinearMatrix dopMatrix = new LinearMatrix(n);
            LinearMatrix answers = new LinearMatrix(n);

            new MatrixCreator().personalVariables(matrix, dopMatrix);
            System.out.println("Your matrix: " + matrix);
            System.out.println("Dop matrix: " + dopMatrix);

            System.out.println("\nChoose a solution method.\nCramer of Gauss");
            String solutionMethod = scanner.next();
            solutionMethod = solutionMethod.trim().toLowerCase();

            if (solutionMethod.equals("cramer")) {
                answers = new CramerMethod(matrix, dopMatrix, answers).solving();
            } else if (solutionMethod.equals("gauss")) {
                answers = new GaussMethod(matrix, dopMatrix, answers).solving();
            } else {
                System.out.println("i don't know such a method");
            }

            System.out.println("Solve: " + answers);
        } catch (MatrixException ex) {
            System.err.println("Error of creating matrix " + ex);
        }
    }
}
