import Assay.MatrixException;
import Matrix.*;
import SolvingMethods.*;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the order of the matrix: ");
        int n = scanner.nextInt();
        try {
            Matrix matrix = new Matrix(n, n);
            LinearMatrix dopMatrix = new LinearMatrix(n);
            LinearMatrix answers = new LinearMatrix(n);

            new MatrixCreator().personalVariables(matrix, dopMatrix);


            System.out.print("Your matrix: " + matrix);
            System.out.println("Dop matrix: " + dopMatrix);

            System.out.println("Choose a solution method.\nCramer of Gauss");
            String solutionMethod = scanner.nextLine();
            solutionMethod = solutionMethod.trim().toLowerCase();

            if (solutionMethod.equals("cramer")) {
                answers = new CramerMethod(matrix, dopMatrix, answers).solve();
            } else if (solutionMethod.equals("gauss")) {
                answers = new GaussMethod(matrix, dopMatrix, answers).solve();
            } else if (solutionMethod.equals("lu") || solutionMethod.equals("lower upper")){
               answers = new LowerUpper(matrix, dopMatrix, answers).solve();
            } else{
                System.out.println("i don't know such a method");
            }

            System.out.println("\nSolution: " + answers);
        } catch (MatrixException ex) {
            System.err.println("Error of creating matrix " + ex);
        }
    }
}
