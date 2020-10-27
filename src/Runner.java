import Assay.MatrixException;
import Matrix.*;
import SolvingMethods.DecompositionMethods.LowerUpper;
import SolvingMethods.DecompositionMethods.SquareRoot;
import SolvingMethods.DirectMethods.CramerMethod;
import SolvingMethods.DirectMethods.GaussMethod;

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

            System.out.println("Choose a solution method." +
                    "\nCramer" +
                    "\nGauss" +
                    "\nLower Upper (LU) decomposition" +
                    "\nSquare root method (Cholesky shame)");
            String solutionMethod = scanner.nextLine();
            solutionMethod = solutionMethod.toLowerCase();

            switch (solutionMethod) {
                case "cramer":
                case "cr":
                    answers = new CramerMethod(matrix, dopMatrix, answers).solve();
                    break;
                case "gauss":
                case "g":
                    answers = new GaussMethod(matrix, dopMatrix, answers).solve();
                    break;
                case "lu":
                case "lower upper":
                case "lower upper decomposition":
                    answers = new LowerUpper(matrix, dopMatrix, answers).solve();
                    break;
                case "square root":
                case "sr":
                case "cholesky":
                    System.out.println("NOTICE\nThis method just for symmetric matrix" +
                            "\nIn case of entering a matrix of non-symmetric type" +
                            "\nThe answer may be incorrect");
                    answers = new SquareRoot(matrix, dopMatrix, answers).solve();
                    break;
                default:
                    System.out.println("i don't know such a method");
                    break;
            }

            System.out.println("\nSolution: " + answers);
        } catch (MatrixException ex) {
            System.err.println("Error of creating matrix " + ex);
        }
    }
}
